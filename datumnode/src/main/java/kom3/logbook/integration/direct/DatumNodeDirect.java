package kom3.logbook.integration.direct;


import logbook.dbl.exchange.ExchangeObject;
import logbook.dbl.exchange.File;
import logbook.dbl.system.Abonent;
import logbook.dbl.system.User;
import logbook.dbl.system.schema.Schema;
import logbook.intergation.commons.Base;
import logbook.intergation.exceptions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import org.apache.camel.Message;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.processor.validation.PredicateValidationException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

import static logbook.intergation.commons.Base.geth;


@Component
public class DatumNodeDirect extends RouteBuilder {


    @Value("${cfg.etc.catalog}")
    String fileStore;

    @Autowired
    logbook.dbl.system.schema.Schemas schemas;

    @Autowired
    logbook.dbl.system.Dictionaries dictionaries;

    @Autowired
    logbook.dbl.system.Users users;

    @Autowired
    logbook.dbl.exchange.ExchangeObjects exchangeObjects;

    @Autowired
    logbook.dbl.exchange.Files files;

    @Autowired
    logbook.dbl.system.Abonents abonents;


    @Override
    public void configure() {
        onException(Exception.class)
                .toD("direct:onException");

        Predicate hasExchange = xpath("boolean(//*[local-name() = 'exchange'])").isEqualTo(true);
        Predicate hasDataType = xpath("boolean(//*[local-name() = 'exchange']/@dataType)").isEqualTo(true);


        from("direct:get-datum-node")
                .routeId("direct:get.datum.node")
                .pollEnrich().simple("file:{{report.book.etc}}/datumnode?fileName=service.wsdl&noop=true&idempotent=false")
                .setHeader("Content-Type").constant("text/xml; charset=UTF-8")
        ;

        from("direct:post-datum-node")
                .doTry()
                .routeId("direct:post.datum.node")
                .process(e -> {
                    System.out.println("soap");
                })

                .setHeader("Content-Type").constant("text/xml; charset=UTF-8")
                .setHeader("sessionId").xpath("//*[local-name() = 'exchange']/@sessionId", String.class)
                .setHeader("dataType").xpath("//*[local-name() = 'exchange']/@dataType", String.class)
                .setHeader("initiator").xpath("//*[local-name() = 'exchange']/@initiator", String.class)
                .setHeader("uuid").xpath("//*[local-name() = 'exchange']/@uuid", String.class)
                .setHeader("messageType").xpath("//*[local-name() = 'message']/@type", String.class)
                .setHeader("attachName").xpath("//*[local-name() = 'attach']/@name", String.class)
                .setHeader("Content-Disposition").simple("filename=\"${header.attachName}\"")

                .setProperty("code").constant("1000")
                .setProperty("result").constant("Сообщение получено")
                .setProperty("description").constant("")

                .setHeader("exchange").xpath("//*[local-name() = 'exchange']", String.class)

                .to("direct:validate")
                .doCatch(EtpmvException.class)
                .setProperty("code").simple("${exception.code}")
                .setProperty("result").simple("${exception.result}")
                .setProperty("description").simple("${exception.desc}")
                .id("Exception.Etpmv")
                .doFinally()

                .process(e -> { //todo вывести обработку datatype в отдельный процессор
//                    Pattern pattern = Pattern.compile("(urn:\\/\\/)([\\w]+\\/)([\\w]+)(\\/)([\\w|\\.]+)(\\/.+)");
//                    Matcher matcher = pattern.matcher(e.getIn().getHeader("dataType", String.class));
//                    e.getIn().setHeader("organization", matcher.replaceFirst("$3"));
                    Schema schema = schemas.findByNamespace(e.getIn().getHeader("dataType", String.class)).orElse(null);
                    e.getIn().setHeader("organization", schema.getProvider().getCode());

                    String uuid = e.getIn().getHeader("uuid", String.class);
                    if (uuid == null || uuid.isEmpty()) uuid = UUID.randomUUID().toString();
                    e.getIn().setHeader("uuid", uuid);
                })
                .choice()
                //                     .when(header("attachName").isNotEqualTo(""))
                .when(header("initiator").isEqualTo(header("organization")))
                // публикация
                .doTry()
                .setHeader("isLast").xpath("//*[local-name() = 'exchange']/@isLast", String.class)
                .setHeader("seqNum").xpath("//*[local-name() = 'exchange']/@seqNum", String.class)

                .toD("direct:validate2")
                .doCatch(EtpmvException.class)
                .setProperty("code").simple("${exception.code}")
                .setProperty("result").simple("${exception.result}")
                .setProperty("description").simple("${exception.desc}")
                .endDoTry()
                .endDoTry()
                .end()

                .setBody().xpath("//*[local-name()='attach']/text()", String.class)
                .process(e -> {
                    byte[] bytes = Base64.getDecoder().decode(e.getIn().getBody(String.class).trim());
                    e.getIn().setHeader("fileLength", Long.valueOf(bytes.length));
                    e.getIn().setBody(bytes);
                })
                .setHeader("Content-Type").simple("application/octet-stream")
                .wireTap("direct:ecm:post:file:byuuid")
                .endChoice().end()

                // todo
                /*.validate(header("dataType").regex("(urn:\\/\\/)([\\w]+\\/){2}([\\w]+)(\\/.+)"))*/
                .toD("activemq:{{datno.messages.in}}?exchangePattern=InOnly")

                .setHeader("Content-Type").simple("text/xml; charset=UTF-8")
                .setBody().constant("<t></t>")
                .toD("xslt:file:{{report.book.etc}}/datumnode/status.xsl")
                .removeHeaders("*", "Content-Type", "sessionId")
                .end()
        ;

        from("direct:datno.messages.in")
                .routeId("direct:datno.messages.in")
                .setHeader("cfg.etc.catalog").simple("{{cfg.etc.catalog}}")
                .process(e -> {
                    Pattern pattern = Pattern.compile("(urn:\\/\\/)([\\w]+\\/){2}([\\w|\\.]+)(\\/.+)");
                    Matcher matcher = pattern.matcher(e.getIn().getHeader("dataType", String.class));

//                    Schema schema = schemas.findByCode(matcher.replaceFirst("$3")).orElse(null);
                    Schema schema = schemas.findByNamespace(e.getIn().getHeader("dataType", String.class)).orElse(null);
                    User user = users.findByLogin("datumnode").orElse(null);
                    String sessionId = e.getIn().getHeader("sessionId", String.class);
                    String uuid = e.getIn().getHeader("uuid", String.class);
                    if (uuid == null || uuid.isEmpty()) uuid = UUID.randomUUID().toString();
                    String initiator = geth(e, "initiator", String.class);

                    Long seqNum = geth(e, "seqNum", Long.class);
                    Boolean isLast = geth(e, "isLast", Boolean.class);

                    ExchangeObject report = new ExchangeObject(schema, user, sessionId, uuid, initiator, isLast,seqNum, new Date(), null, null, new Date());

                    report.setStatus(dictionaries.findOneByCategoryAliasAndArticleValue("reportStatuses", "requestGenerated").orElse(null));
                    ExchangeObject exchangeObject = exchangeObjects.save(report);

                    String dataDir = e.getIn().getHeader("cfg.etc.catalog", String.class);

                    if (Files.exists(Paths.get(dataDir, uuid))) {
                        files.save(new File(exchangeObject, e.getIn().getHeader("uuid", String.class), e.getIn().getHeader("attachName", String.class).trim(), e.getIn().getHeader("fileLength", Long.class)));
                    }
                    HashMap<String, String> request = new HashMap<>();
                    request.put("reportId", exchangeObject.getId().toString());
                    e.getIn().setBody(request);

                })
                .marshal().json()
                .toD("activemq:{{int.messages.in}}?exchangePattern=InOnly")
        ;


        from("direct:validate2")
                .routeId("validate2")
                .errorHandler(noErrorHandler())


// MissingIsLastSeqNum
                .doTry()
                .choice()
                .when(simple("${header.isLast} != ''"))
                .validate(header("seqNum").isNotEqualTo(""))
                .endChoice()
                .when(simple("${header.seqNum} != ''"))
                .validate(header("isLast").isNotEqualTo(""))
                .end()
                .endDoTry()
                .doCatch(PredicateValidationException.class)
                .throwException(new MissingIsLast())
                .end()

// IsLastSeqNumFormat 1013
                .doTry()
                .choice()
                .when(simple("${header.isLast} != ''"))
                .validate(header("isLast").regex("^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee])$"))
                .validate(header("seqNum").regex("[0-9]+"))
                .end()
                .endDoTry()
                .doCatch(PredicateValidationException.class)
                .throwException(new WrongIsLastSeqNumFormat())
                .end()
        ;

        from("direct:validate")
                .routeId("validate")
                .errorHandler(noErrorHandler())
// MissingDatatypeAttribute 1004
                .doTry()
                .validate(header("dataType").isNotEqualTo(""))
                .endDoTry()
                .doCatch(PredicateValidationException.class)
                .throwException(new MissingDatatypeAttribute())
                .end()

// MissingExchangeElement 1002
                .doTry()
                .validate(header("exchange").isNotEqualTo(""))
                .endDoTry()
                .doCatch(PredicateValidationException.class)
                .throwException(new MissingExchangeElement())
                .end()

// MissingSessionIdAttribute 1005
                .doTry()
                .validate(header("sessionId").isNotEqualTo(""))
                .endDoTry()
                .doCatch(PredicateValidationException.class)
                .throwException(new MissingSessionIdAttribute())
                .end()

// WrongSessionIdFormat 1009
                .doTry()
                .validate(header("sessionId").regex("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}"))
                .endDoTry()
                .doCatch(PredicateValidationException.class)
                .throwException(new WrongSessionIdFormat())
                .end()

// WrongUuidFormat 1010 todo

//WrongDatatypeFormat 1007
                .doTry()
                .validate(header("dataType").regex("(urn:\\/\\/)([\\w]+\\/){2}([\\w].+)(\\/.+)"))
                .endDoTry()
                .doCatch(PredicateValidationException.class)
                .throwException(new WrongDatatypeFormat())
                .end()

//Wrong Message type 1014
//                .doTry()
//                .doTry()
//                .validate().simple("${in.header.messageType} == 'text' || ${in.header.messageType} == 'xml' || ${in.header.messageType} == 'json' || ${in.header.messageType} == 'yml'")
//                .endDoTry()
//                .doCatch(PredicateValidationException.class)
//                .throwException(new WrongMessageType())
//                .end()
        ;

        from("direct:datno.files.in")
                .routeId("direct:datno.files.in")

                .process(e -> {
                    String uuid = e.getIn().getHeader("xExchID", String.class);
                    String name = e.getIn().getHeader("xFileName", String.class);

                    List<File> archives = this.files.filesByExchangeUuidAndName(uuid, name);
                    java.io.File archive = Paths.get(fileStore, archives.get(0).getUuid(), "body").toFile();

                    List<File> parts = this.files.partsByExchangeUuidAndName(uuid, name);
                    if (parts.size() == 0) return;

                    archive.delete();
                    for (File part : parts) {
                        part.setDeleted(true);
                        files.save(part);
                        FileUtils.writeByteArrayToFile(archive, Files.readAllBytes(Paths.get(fileStore, part.getUuid(), "body")), true);
                    }
                })

                .toD("direct:datno.messages.out")
        ;

        from("direct:datno.messages.out")
                .routeId("direct:datno.messages.out")
                .process(e -> {
                    String uuid = e.getIn().getHeader("xExchID", String.class);
                    String name = e.getIn().getHeader("@fileName", String.class).trim();

                    ExchangeObject report = exchangeObjects.findByUuid(uuid).orElse(null);

                    Message in = e.getIn();
                    in.setHeader("dataType", report.getSchema().getNamespace());
                    in.setHeader("initiator", report.getInitiator());
                    in.setHeader("sessionId", report.getSessionId());
                    in.setHeader("messageType", "text");
                    in.setHeader("fileName", name);
                    in.setHeader("uuid", UUID.randomUUID().toString());

                    List<File> archives = this.files.filesByExchangeUuidAndName(uuid, name);
                    String content$ = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(fileStore, archives.get(0).getUuid(), "body")));
                    e.getIn().setBody("<filePartContent>" + content$ + "</filePartContent>");

                    Abonent to = abonents.findByCode(report.getInitiator()).orElse(null);
                    String endpoint = to.getEndpoint();

                    in.setHeader("endpoint", endpoint);
                    in.setHeader("SOAPAction", "put");
                    in.setHeader("Content-Type", "text/xml;charset=UTF-8");
                    in.setHeader("xDataMethodValue", "..");

                    in.setHeader("xEventUUID", UUID.randomUUID().toString());

                    in.setHeader("code", "1220");
                    in.setHeader("description", "fns:" + new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSSZ").format(new Date()));
                })
                .toD("xslt:file:{{report.book.etc}}/datumnode/exchange.xsl")
                .toD("direct:log-event-request")
                .setHeader("Authorization", simple("{{opn.auth.header}}"))
                .toD("${header.endpoint}")
                .wireTap("direct:log-event-response")
        ;


        from("activemq:{{datno.messages.in}}")
                .toD("direct:datno.messages.in")
        ;

        from("activemq:{{datno.messages.out}}")
                .process(Base::extractHeaders)
                .toD("direct:datno.messages.out")
        ;

        from("activemq:{{datno.files.in}}")
                .process(Base::extractHeaders)
                .toD("direct:datno.files.in")
        ;
    }

//    public void onExceptions() {
//// NoSubscribesException
//        onException(Exception.class)
//                .handled(false)
////                .routeId("NoSubscribesException")
////                .setProperty("X-Event-Desc").simple("No subscribers found")
////                .setBody(simple("${exchangeProperty.CamelExceptionCaught}"))
//                .process(e -> {
//                    System.out.println("1");
//                })
////                .to("direct:log-event-response")
//                .to("direct:onException")
//        ;
//    }

    ;
}
