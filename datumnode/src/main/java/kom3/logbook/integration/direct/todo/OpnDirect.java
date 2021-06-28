package kom3.logbook.integration.direct.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import logbook.dbl.exchange.ExchangeObject;
import logbook.dbl.exchange.File;
import logbook.dbl.exchange.note.NoteObject;
import logbook.dbl.system.Abonent;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static logbook.intergation.commons.Base.*;

@Component
public class OpnDirect extends RouteBuilder {

    @Autowired
    logbook.dbl.exchange.ExchangeObjects reports;

    @Autowired
    logbook.dbl.exchange.note.NoteObjects notes;

    @Autowired
    logbook.dbl.exchange.ExchangeObjects exchangeObjects;

    @Autowired
    logbook.dbl.system.Abonents abonents;

    private static void extractHeaders(org.apache.camel.Exchange e) {
        try {
            Map map = new ObjectMapper().readValue(e.getIn().getBody(String.class), Map.class);
            map.forEach((k, v) -> e.getIn().setHeader(String.valueOf(k), v));
        } catch (IOException ioException) {

        }
        e.getIn().setBody("<o/>");
    }

    @Override
    public void configure() {

        onException(Exception.class)
                .toD("direct:onException")
        ;

        from("direct:opn.data.out")
                .routeId("direct:opn.data.out")

                .setHeader("@reportUuid", simple("${header.xExchId}"))
                .process(e -> {
                    String reporUuid = $$(geth(e, "@reportUuid", String.class), "1L");
                    ExchangeObject report = reports.findByUuid(reporUuid).orElse(new ExchangeObject());
                    String fileUuid = report.getFiles().stream()
                            .sorted(Comparator.comparing(File::getCreated).reversed()).findFirst().orElse(new File()).getUuid();

                    e.getIn().setHeader("@fileUuid", fileUuid);
                    e.getIn().setHeader("uuid", fileUuid);
                })

                .toD("direct:ecm:get:file:byuuid")

                .process(e -> {
                    String s = new String(e.getIn().getBody(byte[].class));
                    e.getIn().setBody(s);
                    org.json.JSONObject obj = new org.json.JSONObject(s);
                    String xml_data = org.json.XML.toString(obj);
                    e.getIn().setBody(xml_data);
                })
                .setHeader("content-type", constant("application/xml"))
        ;

        from("direct:opn.messages.out")
                .routeId("direct:opn.messages.out")

                .choice()
                .when().simple("${header.xApproversCount} == null || ${header.xApproversCount} == 0")
                .toD("direct:opn.messages.do")

                .when().simple("${header.xApproversCount} < 0")
                .toD("activemq:{{opn.messages.er}}?exchangePattern=InOnly")

                .otherwise()
                .process(e -> {
                    ExchangeObject report = reports.findByUuid(geth(e, "xExchID", String.class)).orElse(new ExchangeObject());

                    Long xApproversCount = geth(e, "xApproversCount", Long.class);
                    for (NoteObject n : report.getNotes()) {
                        if (!n.getDeleted()) {
                            String articleValue = n.getStatus().getArticleValue();
                            if ("canceled".equals(articleValue)) {
                                xApproversCount = -1L;
                                n.setDeleted(true);
                                notes.save(n);
                                break;
                            }
                            if ("approved".equals(articleValue)) {
                                xApproversCount--;
                                n.setDeleted(true);
                                notes.save(n);
                            }
                        }
                    }
                    e.getIn().setHeader("xApproversCount", xApproversCount);

                })
                .toD("activemq:{{opn.messages.bk}}?exchangePattern=InOnly")
                .end()
        ;

        from("direct:opn.messages.do")
                .setHeader("xDataMethodCategory").constant("urn:SendMessageRequest")
                .setHeader("xDataMethodValue").constant("putReport")

                .process(e -> {
                    Map<String, Object> headers = e.getIn().getHeaders();
                    headers.forEach((k, v) -> e.setProperty(k, v));
                    String reportUUID = e.getIn().getHeader("xExchID", String.class);
                    logbook.dbl.exchange.ExchangeObject report = reports.findByUuid(reportUUID).orElse(new logbook.dbl.exchange.ExchangeObject());

                    report.setStarted(new Date());
                    reports.save(report);

                    Set<File> files = report.getFiles();
                    String uuid = files.stream()
                            .sorted(Comparator.comparing(File::getCreated).reversed()).findFirst().orElse(new File()).getUuid();
                    e.getIn().setHeader("uuid", uuid);
                })

                .toD("direct:ecm:get:file:byuuid")

                .process(e -> {
                    byte[] bytes = e.getIn().getBody(byte[].class);
                    String content$ = Base64.getEncoder().encodeToString(bytes);

                    String disposition = getOrDefault(e.getIn().getHeader("Content-Disposition", String.class), "filename=\"body\"");
                    String fileName = disposition.replaceFirst("^.*filename=\"?([^\"]+)\"?.*$", "$1").trim();

                    e.getIn().setHeader("xFileName", fileName);
                    e.getIn().setHeader("xFileSize", bytes.length);
                    e.getIn().setBody("<filePartContent>" + content$ + "</filePartContent>");
                    e.getProperties().forEach((k, v) -> e.getIn().setHeader(k, v));
                })

                .toD("direct:goto-fns-svc")

                .setHeader("xMessageId").xpath("//*[local-name()='MessageId'][1]/text()", String.class)

                .wireTap("direct:fns.requests.in")
        ;

        from("direct:opn.ack.in")
                .routeId("direct:opn.ack.in")
//                .setHeader("xTicketId").simple("${header.@ticketId}")
                .removeHeaders("*", "xExchID", "xReqGid")
                .process(e -> {
                    String uuid = e.getIn().getHeader("xExchID", String.class);

                    ExchangeObject report = exchangeObjects.findByUuid(uuid).orElse(null);

                    Message in = e.getIn();
                    in.setHeader("dataType", report.getSchema().getNamespace());
                    in.setHeader("provider", report.getSchema().getProvider().getCode());
                    in.setHeader("initiator", report.getInitiator());
                    in.setHeader("sessionId", report.getSessionId());
                    in.setHeader("uuid", UUID.randomUUID().toString());

                    Abonent to = abonents.findByCode(report.getInitiator()).orElse(null);
                    String endpoint = to.getEndpoint();

                    in.setHeader("endpoint", endpoint);
                    in.setHeader("SOAPAction", "put");
                    in.setHeader("Content-Type", "text/xml;charset=UTF-8");
                    in.setHeader("xDataMethodValue", "status");

                    in.setHeader("code", "1200");
                    in.setHeader("description", "fns:" + new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSSZ").format(new Date()));

//                    in.setHeader("xEventUUID", UUID.randomUUID().toString());
                })
//                .choice()
//                .when(header("initiator").isEqualTo(header("provider")))
                .toD("xslt:file:{{report.book.etc}}/datumnode/answer.xsl")
                .setHeader("Authorization", simple("{{opn.auth.header}}"))
                .toD("direct:log-event-request")
                .toD("${header.endpoint}")
                .wireTap("direct:log-event-response")
//                .endChoice().end()
        ;


        from("activemq:{{opn.data.out}}")
                .process(OpnDirect::extractHeaders)
                .toD("direct:opn.data.out")
        ;

        from("activemq:{{opn.messages.out}}")
                .process(OpnDirect::extractHeaders)
                .toD("direct:opn.messages.out")
        ;

        from("activemq:{{opn.ack.in}}")
                .process(OpnDirect::extractHeaders)
                .toD("direct:opn.ack.in")
        ;
    }
}

