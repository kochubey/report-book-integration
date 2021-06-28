package kom3.logbook.integration.direct.adapter;

import logbook.dbl.exchange.File;
import logbook.intergation.commons.AddProperty;
import logbook.intergation.commons.Base;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import static logbook.intergation.commons.Base.getOrDefault;
import static logbook.intergation.commons.Base.geth;

@Component
public class FnsDirect extends RouteBuilder {

    public static final String XSLT_DATA_METHOD = "xslt:file:{{report.book.etc}}/fns/${header.xDataMethodValue}.xsl";

    @Value("${datno.ttl.delay}")
    Long ttl;

    @Autowired
    logbook.dbl.exchange.ExchangeObjects reports;

    @Autowired
    logbook.dbl.exchange.Files files;

    @Override
    public void configure() {

        onException(Exception.class)
                .toD("direct:onException")
        ;

        from("direct:fns.messages.in")
                .routeId("direct:fns.messages.in")

                .setHeader("xDataMethodCategory").constant("urn:SendMessageRequest")
                .process(e -> {
                    String provider = getOrDefault(geth(e, "xDataProvider", String.class), "fns");
//                    String action = getOrDefault(e.getIn().getHeader("xDataMethodValue", String.class), "getCorrectedReport");
                    String action = provider.equals("fns") ? "getCorrectedReport" : "putReport";
                    e.getIn().setHeader("xDataMethodValue", action);
                })

                .toD("direct:goto-fns-svc")
                .setHeader("xMessageId").xpath("//*[local-name()='MessageId'][1]/text()", String.class)

                .process(e -> {
                    Date started = new Date();
                    e.getIn().setHeader("xExchStarted", started.getTime());

                    String uuid = e.getIn().getHeader("xExchID", String.class);
                    logbook.dbl.exchange.ExchangeObject report = reports.findByUuid(uuid).orElse(null);
                    report.setStarted(started);
                    reports.save(report);
                })
                .wireTap("direct:fns.requests.in")
        ;

        from("direct:check-ttl")
                .process(e -> {
                    Long started = e.getIn().getHeader("xExchStarted", Long.class);
                    if (started == null) {
                        started = new Date().getTime();
                        e.getIn().setHeader("xExchStarted", started);
                    }
                    Long current = new Date().getTime();
                    if (current - started >= ttl) {
                        e.getIn().setHeader("xExchTTL", "86400000");
                    }
                })
        ;

        from("direct:fns.requests.in")
                .routeId("direct:fns.requests.in")

                .removeHeaders("*", "x*")
                .removeProperties("*", "x*")

                .setHeader("xDataMethodValue").constant("getMessage")
                .setHeader("xDataMethodCategory").constant("urn:GetMessageRequest")
                .toD("direct:loop-fns-svc")

                .setHeader("@ticketId").xpath("//*[local-name()='result']/@ticketId", String.class)
                .setHeader("@resultCode").xpath("//*/@resultCode", String.class)

                .toD("direct:check-ttl")

                .choice()
                .when(simple("${header.xExchTTL} == '86400000'"))
                .toD("activemq:{{fns.requests.er}}?exchangePattern=InOnly")
                .when(simple("${header.@resultCode.toString()} == 'INTERNAL_SERVER_ERROR'"))
                .toD("activemq:{{fns.requests.er}}?exchangePattern=InOnly")
                .when(simple("${header.@ticketId.toString()} == ''"))
                .toD("activemq:{{fns.messages.bk}}?exchangePattern=InOnly")
                .otherwise()
                .setHeader("xTicketId").simple("${header.@ticketId}")
                .wireTap("activemq:{{opn.ack.in}}?exchangePattern=InOnly")
                .process(e -> {
                    String uuid = e.getIn().getHeader("xExchID", String.class);
                    logbook.dbl.exchange.ExchangeObject report = reports.findByUuid(uuid).orElse(null);
                    report.setLid(e.getIn().getHeader("xTicketId", String.class));
                    reports.save(report);
                })
                .wireTap("direct:fns.tickets.in")
                .endChoice()
        ;

        from("direct:fns.tickets.in")
                .process(e -> {
                    System.out.println("3");
                })

                .routeId("direct:fns.tickets.in")
                .removeHeaders("*", "x*")
                .removeProperties("*", "x*")

                .setHeader("xDataMethodValue").constant("getTicketStatus")
                .setHeader("xDataMethodCategory").constant("urn:SendMessageRequest")
                .toD("direct:goto-fns-svc")

                .setHeader("xMessageId").xpath("//*[local-name()='MessageId'][1]/text()", String.class)

                .wireTap("direct:fns.results.in")
        ;

        from("direct:fns.results.in")
                .process(e -> {
                    System.out.println("4");
                })
                .routeId("direct:fns.results.in")

                .removeHeaders("*", "x*")
                .removeProperties("*", "x*")

                .setHeader("xDataMethodValue").constant("getMessage")
                .setHeader("xDataMethodCategory").constant("urn:GetMessageRequest")
                .toD("direct:loop-fns-svc")

                .setHeader("@messageId").xpath("//*/@messageId", String.class)
                .setHeader("@resultCode").xpath("//*[@resultCode != '']/@resultCode", String.class)

                .toD("direct:check-ttl")

                .choice()
                .when(simple("${header.xExchTTL} == '86400000'"))
                .toD("activemq:{{fns.results.er}}?exchangePattern=InOnly")
                .when(simple("${header.@resultCode.toString()} == 'INTERNAL_SERVER_ERROR'"))
                .toD("activemq:{{fns.results.er}}?exchangePattern=InOnly")
                .when(simple("${header.@messageId.toString()} == ''"))
                .toD("activemq:{{fns.results.bk}}?exchangePattern=InOnly")
                .otherwise()

                .toD("direct:fns.reports.in")
                .endChoice()
        ;

        from("direct:fns.reports.in")
                .process(e -> {
                    System.out.println("5");
                })
                .routeId("direct:fns.reports.in")

                .removeHeaders("*", "x*")
                .removeProperties("*", "x*")

                .setHeader("xDataMethodCategory").constant("urn:SendMessageRequest")
                .setHeader("xDataMethodValue").constant("getReportContent")
                .toD("direct:goto-fns-svc")

                .setHeader("xDataMethodCategory").constant("urn:GetMessageRequest")
                .setHeader("xDataMethodValue").constant("getMessage")
                .setHeader("xMessageId").xpath("//*[local-name()='MessageId'][1]/text()", String.class)
                .toD("direct:loop-fns-svc")

                .setHeader("@resultCode").xpath("//*[@resultCode != '']/@resultCode", String.class)
                .setHeader("@filePartCount").xpath("//*/@filePartCount", String.class)
                .setHeader("@fileName").xpath("//*[local-name()='fileName']/text()", String.class)
                .setHeader("xFileName").xpath("//*[local-name()='fileName']/text()", String.class)

                .toD("direct:check-ttl")

                .choice()

                .when(simple("${header.xExchTTL} == '86400000'"))
                .toD("activemq:{{fns.reports.er}}?exchangePattern=InOnly")

                .when(simple("${header.@resultCode.toString()} == 'INTERNAL_SERVER_ERROR'"))
                .toD("activemq:{{fns.reports.er}}?exchangePattern=InOnly")

                .when(simple("${header.@filePartCount} == null || ${header.@filePartCount} == ''"))
                .toD("activemq:{{fns.reports.bk}}?exchangePattern=InOnly")

                .when(simple("${header.@filePartCount} == 1"))
                .process(e -> {
                    String uuid = e.getIn().getHeader("xExchID", String.class);
                    logbook.dbl.exchange.ExchangeObject report = reports.findByUuid(uuid).orElse(null);
                    report.setFinished(new Date());
                    reports.save(report);
                })
                .toD("direct:fns.acks.in")

                .when(simple("${header.@filePartCount} > 1"))
                .setHeader("xFilePartCount").simple("${header.@filePartCount}")
                .setHeader("xFileName").simple("${header.@fileName}")

                .setProperty("CamelLoopIndex").constant(1L)
                .loop(simple("${header.xFilePartCount}"))
                .setHeader("xPartNumber").simple("${header.CamelLoopIndex}++")
                .process(e -> {
                    System.out.println("11." + e.getIn().getHeader("xPartNumber"));
                })
                .toD("xslt:file:{{report.book.etc}}/fns/load/cutFilePartContent.xsl")
                .toD("activemq:{{fns.parts.in}}?exchangePattern=InOnly")
                .end()

                .endChoice()
        ;

        from("direct:fns.parts.in")
                .process(e -> {
                    System.out.println("12");
                })
                .routeId("direct:fns.parts.in")

                .removeHeaders("*", "x*")
                .removeProperties("*", "x*")

                .setHeader("xDataMethodCategory").constant("urn:SendMessageRequest")
                .setHeader("xDataMethodValue").constant("getReportContentPartRequest")
                .toD("direct:goto-fns-svc")

                .setHeader("xDataMethodCategory").constant("urn:GetMessageRequest")
                .setHeader("xDataMethodValue").constant("getMessage")
                .setHeader("xMessageId").xpath("//*[local-name()='MessageId'][1]/text()", String.class)
                .toD("direct:loop-fns-svc")

                .setHeader("@resultCode").xpath("//*[@resultCode != '']/@resultCode", String.class)
                .setHeader("@filePartCount").xpath("//*/@filePartCount", String.class)
                .setHeader("@filePartNumber").xpath("//*/@filePartNumber", String.class)
// todo имя файла при загрузке по частям пустое нужно использовать глобальное
//                .setHeader("@fileName").xpath("//*[local-name()='fileName']/text()", String.class)

                .choice()

                .when(simple("${header.@resultCode.toString()} == 'INTERNAL_SERVER_ERROR'"))
                .toD("activemq:{{fns.parts.er}}?exchangePattern=InOnly")

                .when(simple("${header.@filePartNumber} == null || ${header.@filePartNumber} == ''"))
                .toD("activemq:{{fns.parts.bk}}?exchangePattern=InOnly")

                .when().simple("${header.@filePartCount} == ${header.@filePartNumber}")
                .process(e -> {
                    String uuid = e.getIn().getHeader("xExchID", String.class);
                    logbook.dbl.exchange.ExchangeObject report = reports.findByUuid(uuid).orElse(null);
                    report.setFinished(new Date());
                    reports.save(report);
                })
                .toD("direct:fns.acks.in")

                .endChoice()
        ;

        from("direct:fns.acks.in")
                .process(e -> {
                    System.out.println("13");
                })
                .routeId("direct:fns.acks.in")

                .setHeader("xFileName").xpath("//*[local-name()='fileName'][1]/text()", String.class)
                .toD("direct:datno.files.in")

                .removeHeaders("*", "x*")
                .removeProperties("*", "x*")

                .setHeader("xDataMethodCategory").constant("urn:SendMessageRequest")
                .setHeader("xDataMethodValue").constant("reportReceived")
                .toD("direct:goto-fns-svc")

                .setHeader("xDataMethodCategory").constant("urn:GetMessageRequest")
                .setHeader("xDataMethodValue").constant("getMessage")
                .setHeader("xMessageId").xpath("//*[local-name()='MessageId'][1]/text()", String.class)
                .toD("direct:loop-fns-svc")

                .setHeader("@resultCode").xpath("//*[@resultCode != '']/@resultCode", String.class)
                .setHeader("@resultCodeType").xpath("//*/@resultCodeType", String.class)

                .toD("direct:check-ttl")

                .choice()

                .when(simple("${header.xExchTTL} == '86400000'"))
                .toD("activemq:{{fns.acks.er}}?exchangePattern=InOnly")

                .when(simple("${header.@resultCode.toString()} == 'INTERNAL_SERVER_ERROR'"))
                .toD("activemq:{{fns.acks.er}}?exchangePattern=InOnly")

                .when(simple("${header.@resultCodeType} == null || ${header.@resultCodeType} == ''"))
                .toD("activemq:{{fns.acks.bk}}?exchangePattern=InOnly")

                .end()

                .process(e -> {
                    System.out.println("14");
                })
        ;

        from("direct:loop-fns-svc")
                .setProperty("CamelLoopIndex").constant(1)
                .setHeader("ProcessingStatus").constant("")
                .loopDoWhile(   //        start loop
                        simple("${header.ProcessingStatus.toString()} != 'COMPLETED' && ${exchangeProperty.CamelLoopIndex} <= 2"))
                .toD("direct:goto-fns-svc")
                .setHeader("ProcessingStatus").xpath("//*[local-name()='ProcessingStatus'][1]/text()", String.class)
                .choice().when().simple("${header.ProcessingStatus.toString()} != 'COMPLETED'").delay(30000).endChoice()
                .end().end()    //         end loop
        ;

        from("direct:goto-fns-svc")
                .routeId("direct:goto-fns-svc")

                .setHeader("xCurrentDateTime").groovy("new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ss.SSS\").format(new Date())")

                .removeHeaders("*", "x*", "SOAP*")
                .removeProperties("*", "x*", "CamelLoopIndex")

                //  set acl
                .enrich("direct:get-openapi-token", new AddProperty("tokenKey"))
                .setHeader("FNS-OpenApi-Token").simple("${exchangeProperty.tokenKey}")
                .setHeader("FNS-OpenApi-UserToken").simple("{{fns.user.token}}")

                //  set soap request
                .setHeader("SOAPAction", simple("${header.xDataMethodCategory}"))
                .setHeader("Content-Type", constant("application/xml;charset=UTF-8"))
                .toD(XSLT_DATA_METHOD)

                .wireTap("direct:int.histories.in")
                // send + log
                .toD("direct:log-event-request")
                .toD("${header.xDataEndpoint}").convertBodyTo(String.class)
                .toD("direct:log-event-response")
        ;

        from("activemq:{{fns.messages.in}}")
                .process(Base::extractHeaders)
                .toD("direct:fns.messages.in")
        ;

        from("activemq:{{fns.requests.in}}")
                .process(Base::extractHeaders)
                .toD("direct:fns.requests.in")
        ;

        from("activemq:{{fns.tickets.in}}")
                .process(Base::extractHeaders)
                .toD("direct:fns.tickets.in")
        ;

        from("activemq:{{fns.reports.in}}")
                .process(Base::extractHeaders)
                .toD("direct:fns.reports.in")
        ;

        from("activemq:{{fns.parts.in}}")
                .process(Base::extractHeaders)
                .toD("direct:fns.parts.in")
        ;

        from("activemq:{{fns.acks.in}}")
                .process(Base::extractHeaders)
                .toD("direct:fns.acks.in")
        ;

        from("activemq:{{fns.files.out}}")
                .setHeader("uuid").groovy("java.util.UUID.randomUUID().toString()")
                .setHeader("fileName").xpath("//*[local-name()='fileName']/text()", String.class)
                // todo имя файла при передаче по частям пустое
                .choice().when().simple("${header.fileName} == null || ${header.fileName} == ''").setHeader("fileName").simple("${header.xFileName}").end()
                .choice().when().simple("${header.xPartNumber} != null").setHeader("fileName").simple("${header.fileName}.${header.xPartNumber}").end()
                .setHeader("Content-Disposition").simple("filename=${header.fileName}")
                .setHeader("xExchID").xpath("//*/@messageId", String.class)
                .setBody().xpath("//*[local-name()='filePartContent']/text()", String.class)
                .process(e -> {
                    String reportUUID = e.getIn().getHeader("xExchID", String.class);
                    logbook.dbl.exchange.ExchangeObject report = reports.findByUuid(reportUUID).orElse(null);
                    if (report != null) {
                        e.getIn().setHeader("reportId", report.getId());
                        e.getIn().setHeader("reportUUID", report.getUuid());
                    }
                    byte[] bytes = Base64.getDecoder().decode(e.getIn().getBody(String.class).trim());
                    e.getIn().setBody(bytes);

                    File file = new File(report, e.getIn().getHeader("uuid", String.class), e.getIn().getHeader("fileName", String.class).trim(), Long.valueOf(bytes.length));
                    files.save(file);
                })
                .toD("direct:ecm:post:file:byuuid")
        ;

    }
}

