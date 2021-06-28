package kom3.logbook.integration.direct;

import logbook.dbl.exchange.Event;
import logbook.dbl.exchange.ExchangeObject;
import logbook.dbl.exchange.History;
import logbook.intergation.commons.Base;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

import static logbook.intergation.commons.Base.$$;
import static logbook.intergation.commons.Base.geth;


@Component
public class LoggingDirect extends RouteBuilder {
    public static final String XSLT_CUT_FILE_PART_CONTENT = "xslt:file:{{report.book.etc}}/fns/load/cutFilePartContent.xsl";
    public static final String XSLT_CUT_FILE_CONTENT = "xslt:file:{{report.book.etc}}/fns/load/cutFileContent.xsl";
    public static final String XSLT_EXTRACT_EVENT = "xslt:file:{{report.book.etc}}/datumnode/event.xsl";

    @Autowired
    logbook.dbl.exchange.ExchangeObjects reports;

    @Autowired
    logbook.dbl.exchange.Events events;

    @Autowired
    logbook.dbl.exchange.Histories histories;

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .toD("direct:onException");

//        todo нужно выделить общие части рутов в шаблонный метод
        from("direct:log-event-request")
                .setHeader("xEventName", simple("${header.SOAPAction}.${header.xDataMethodValue}"))
                .setHeader("xEventUUID").groovy("java.util.UUID.randomUUID().toString()")
//                .process(e -> {
//                    String eventUuid = e.getIn().getHeader("xEventUUID", String.class);
//                    if (eventUuid == null || eventUuid.isEmpty()) {
//                        e.getIn().setHeader("xEventUUID", UUID.randomUUID().toString());
//                    }
//                })

                .choice()

                .when(xpath("//*[local-name()='${header.filePartContent}']"))
                .toD(XSLT_EXTRACT_EVENT)

                .when().xpath("//*[local-name()='fileContent']")
                .endChoice().end().end()

                .process(e -> {
                    String reportUuid = e.getIn().getHeader("xReqGid", String.class);
                    String eventUuid = e.getIn().getHeader("xEventUUID", String.class);
                    String eventName = e.getIn().getHeader("xEventName", String.class);

                    Event event = events.findByUuid(eventUuid).orElse(new Event(eventUuid));

                    event.setName(eventName);
                    event.setRequestHeaders("{" + e.getIn().getHeaders().entrySet().stream().map(o -> "\"" + o.getKey() + "\"" + ":\"" + o.getValue() + "\"").collect(Collectors.joining(",\n ")) + "}");
                    event.setRequestBody(e.getIn().getBody(String.class));
                    event.setStarted(new Date());

                    event.setExchange(reports.findByUuid(reportUuid).orElse(null));
                    events.save(event);

                    e.getIn().setHeader("JMSMessageID", eventUuid);
                });

        from("direct:log-event-response")
                .convertBodyTo(String.class)
                .choice()

//                .when(simple("${header.exceptionStatusCode} != null && ${header.exceptionStatusCode} != ''"))
                .when(header("exceptionStatusCode").isNotNull())
                .setBody(simple("${header.exceptionDetailMessage}"))

                .when(xpath("//*[local-name()='${header.filePartContent}']"))
                .toD(XSLT_EXTRACT_EVENT)

                .when(xpath("//*[local-name()='filePartContent']"))
                .wireTap("activemq:{{fns.files.out}}?exchangePattern=InOnly")
                .toD(XSLT_CUT_FILE_PART_CONTENT)

                .endChoice().end().end()

                .process(e -> {
                    String reportUuid = e.getIn().getHeader("xReqGid", String.class);
                    String eventUuid = $$(e.getIn().getHeader("xEventUUID", String.class), "");
                    String eventId$ = $$(e.getIn().getHeader("xEventId", String.class), "");
                    String eventName = e.getIn().getHeader("xEventName", String.class);

                    Event event = $$(
                            events.findById(eventId$.isEmpty() ? -1L : Long.valueOf(eventId$)).orElse(null),
                            events.findByUuid(eventUuid).orElse(new Event(eventUuid))
                    );

                    event.setName(event.getName().isEmpty() ? eventName : event.getName());
                    event.setUuid(eventUuid);
                    event.setResponseHeaders("{" + e.getIn().getHeaders().entrySet().stream().map(o -> "\"" + o.getKey() + "\"" + ":\"" + o.getValue() + "\"").collect(Collectors.joining(", ")) + "}");
                    event.setResponseBody(e.getIn().getBody(String.class));

                    event.setFinished(new Date());

                    ExchangeObject exchange = reports.findByUuid(reportUuid).orElse(null);
                    event.setExchange(exchange);

                    events.save(event);
                })
        ;

        from("direct:int.histories.in")
                .setBody(constant("<o/>"))

                .choice()
                .when(simple("${header.reportId} == null || ${header.reportId} == ''"))
                .setHeader("id").simple("${header.xReqId}")
                .log("--")
                .otherwise()
                .setHeader("id").simple("${header.reportId}")
                .end()

                .setHeader("uuid").simple("${header.xExchID}")

                .choice()
                .when(simple("${header.reportAction} == null || ${header.reportAction} == ''"))
                .setHeader("_action").simple("${header._action}")
                .log("--")
                .otherwise()
                .setHeader("_action").simple("${header.reportAction}")
                .end()

                .toD("xslt:file:{{report.book.etc}}/datumnode/action.xsl")
                .setHeader("@id").jsonpath("$.id", true)
                .setHeader("@uuid").jsonpath("$.uuid", true)
                .setHeader("@action").jsonpath("$.action", true)

                .process(e -> {
                    String action = geth(e, "@action", String.class);
                    if (!"" .equals(action)) {
                        String id$ = $$(geth(e, "@id", String.class), "");
                        String uuid$ = $$(geth(e, "@uuid", String.class), "");
                        ExchangeObject report = $$(
                                reports.findById(id$.isEmpty() ? -1L : Long.valueOf(id$)).orElse(null),
                                reports.findByUuid(uuid$).orElse(null)
                        );
                        if (report != null) {
                            histories.save(new History(report, action));
                        }
                    }
                })
        ;

//        from("direct:int.events.in")
//                .setBody(constant("<o/>"))
//                .setHeader("@id").simple("${header.xReqId}")
//                .setHeader("@uuid").simple("${header.xExchID}")
//                .toD("xslt:file:{{report.book.etc}}/datumnode/event.xsl")
//
//                .process(e -> {
//                    if (!"--".equals(action)) {
//                        String id$ = geth(e, "@id", String.class);
//                        String uuid$ = geth(e, "@uuid", String.class);
//                        Exchange report = $$(
//                                reports.findById("".isEmpty()?-1L:Long.valueOf(id$)).orElse(null),
//                                reports.findByUuid(uuid$).orElse(null)
//                        );
//                        if (report != null) {
//                            histories.save(new History(report, action));
//                        }
//                    }
//                })
//        ;

        from("direct:onException")
                .process(e->{
                    System.out.println("1");
                })
                .setHeader("exceptionDetailMessage").simple("${exception}")
                .setHeader("exceptionStatusCode").simple("${exception.statusCode}")
                .setHeader("exceptionResponseHeaders").simple("${exception.responseHeaders}")
                .setHeader("exceptionBody").simple("${exception.responseBody}")

                .choice()
                .when().simple("${header.exceptionStatusCode} == 500 && ${header.exceptionBody contains 'сообщение не найдено'}")
                .setBody().simple("${exception.responseBody}")
                .toD("direct:log-event-response")
                .toD("activemq:{{fns.messages.bk}}?exchangePattern=InOnly")
                .endChoice()
                .when().simple("${header.exceptionStatusCode} == 500")
                .setBody().simple("${exception.responseBody}")
                .toD("direct:log-event-response")
                .endChoice()
                .otherwise()
                .setBody().simple("${exception.responseBody}")
                .wireTap("direct:log-event-response")
                .toD("activemq:{{fns.messages.er}}?exchangePattern=InOnly")
        ;

        from("activemq:{{int.histories.in}}")
                .process(Base::extractProperties)
                .process(Base::extractHeaders)
                .toD("direct:int.histories.in")
        ;

        from("activemq:log.events.request")
                .process(Base::extractProperties)
                .toD("direct:log-event-request")
        ;

        from("activemq:log.events.response")
                .process(Base::extractProperties)
                .toD("direct:log-event-response")
        ;


    }


}
