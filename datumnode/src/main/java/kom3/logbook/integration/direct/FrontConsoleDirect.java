package kom3.logbook.integration.direct;

import com.fasterxml.jackson.databind.ObjectMapper;

import logbook.dbl.exchange.ExchangeObject;
import logbook.dbl.exchange.note.NoteObject;
import logbook.dbl.exchange.route.Approver;
import logbook.dbl.exchange.route.Route;
import logbook.dbl.system.Abonent;
import logbook.dbl.system.Dictionary;
import logbook.dbl.system.User;
import logbook.dbl.system.schema.Schema;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static logbook.intergation.commons.Base.$$;
import static logbook.intergation.commons.Base.geth;

@Component
public class FrontConsoleDirect extends RouteBuilder {


    private static void extractHeaders(org.apache.camel.Exchange e) {
        try {
            Map map = new ObjectMapper().readValue(e.getIn().getBody(String.class), Map.class);
            map.forEach((k, v) -> e.getIn().setHeader(String.valueOf(k), v));
        } catch (IOException ioException) {

        }
        e.getIn().setBody("<o/>");
    }

    @Autowired
    logbook.dbl.exchange.ExchangeObjects reports;

    @Autowired
    logbook.dbl.exchange.note.NoteObjects notes;

    @Autowired
    logbook.dbl.system.Abonents systems;

    @Autowired
    logbook.dbl.system.schema.Schemas schemas;

    @Autowired
    logbook.dbl.system.Dictionaries dicts;

    @Autowired
    logbook.dbl.system.Users users;

    @Override
    public void configure() {
        onException(Exception.class)
                .toD("direct:onException");

        from("activemq:{{int.messages.in}}")
                .process(e -> {
                    System.out.println("0");
                })
                .toD("direct:frontconsole-main-svc")
        ;

        from("activemq:{{int.schedules.in}}")
                .process(FrontConsoleDirect::extractHeaders)
                .process(e -> {
                    String xDataType = e.getIn().getHeader("xDataType", String.class);
                    Schema schema = schemas.findByCode(xDataType).orElse(null);

                    System.out.println("int.schedules.in");

                    User user = users.findById(1L).orElse(null);
                    ExchangeObject report = reports.save(
                            new ExchangeObject(schema, user, null, null, null, new Date()));


                    e.getIn().setHeader("xExchID", report.getUuid());

                    Abonent fns = systems.findByCode(schema.getCode()).orElse(null);

                    String tns = schema.getProvider().getCode();
                    e.getIn().setHeader("xDataProvider", tns);

                    HashMap<String, String> request = new HashMap<>();
                    request.put("xDataProvider", tns);
                    request.put("xDataEndpoint", schema.getProvider().getEndpoint());
                    request.put("xDataSchema", schema.getNamespace());
                    request.put("xReportType", schema.getCode());
                    request.put("xReqGid", report.getUuid());
                    request.put("xReqAuthorId", String.valueOf(report.getAuthor().getId()));

                    SimpleDateFormat oldf = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSSZ");
                    SimpleDateFormat newf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    String format = newf.format(report.getToDate());
                    request.put("xReqDateParam", format.substring(0, 10));

                    String xDataMethodValue = "getScheduledReport";
                    String xDataQueue = "fns.messages.in";
                    if ("opn".equals(tns)) {
                        request.put("xDataEndpoint", fns.getEndpoint());
                        xDataMethodValue = "putReport";
                        xDataQueue = "opn.messages.out";
                        e.getIn().setHeader("xApproversCount", 0L);
                    }
                    e.getIn().setHeader("xDataMethodValue", xDataMethodValue);
                    e.getIn().setHeader("xDataDirect", xDataQueue);

                    e.getIn().setBody(request);
                })
                .marshal().json()
                .toD("activemq:${properties:${header.xDataDirect}}?exchangePattern=InOnly")
        ;

        from("direct:frontconsole-main-svc")
                .routeId("direct-frontconsole-main-svc")

                .process(e -> {
                    System.out.println("0");
                })
//                .setProperty("xExchAction", simple("${header.action}"))
                .removeHeaders("*")

//                .setHeader("xExchAction").simple("${exchangeProperty.xExchAction}")
                .setHeader("xReqId").jsonpath("$.reportId", true)

                //  evaluate
                .setHeader("xExchTS").groovy("new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ss\").format(new Date())")
                .setHeader("xExchID").groovy("java.util.UUID.randomUUID().toString()")

                .transform(constant("<o/>"))
                .toD("xslt:file:{{report.book.etc}}/frontconsole/status.xsl")

                //  getcorrectedreport
                .wireTap("direct:getcorrectedreport")
        ;

        //  todo нужно изменить название
        from("direct:getcorrectedreport")
                .routeId("direct-frontconsole-getcorrectedreport-svc")

                .process(e -> {
                    ExchangeObject report = reports.findById(e.getIn().getHeader("xReqId", Long.class)).orElse(null);
                    e.getIn().setHeader("xExchID", report.getUuid());

                    Abonent fns = systems.findByCode("fns").orElse(null);

                    Schema schema = report.getSchema();
                    String tns = schema.getProvider().getCode();
                    e.getIn().setHeader("xDataProvider", tns);


                    HashMap<String, String> request = new HashMap<>();
                    request.put("xDataProvider", tns);
                    request.put("xDataEndpoint", schema.getProvider().getEndpoint());
                    request.put("xDataSchema", schema.getNamespace());
                    request.put("xReportType", schema.getCode());
                    request.put("xReqGid", report.getUuid());
                    request.put("xReqAuthorId", String.valueOf(report.getAuthor().getId()));

                    SimpleDateFormat oldf = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSSZ");
                    SimpleDateFormat newf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    String format = newf.format(report.getToDate());
                    request.put("xReqDateParam", format.substring(0, 10));

                    String xDataMethodValue = "getCorrectedReport";
                    String xDataQueue = "fns.messages.in";
                    if ("opn".equals(tns)) {
                        request.put("xDataEndpoint", fns.getEndpoint());
                        xDataMethodValue = "putReport";
                        xDataQueue = "opn.messages.out";
//                        xDataQueue = "opn.data.out";


                        Dictionary approve = dicts.findOneByArticleValue("approve").orElse(null);
                        Dictionary waiting = dicts.findOneByArticleValue("waiting").orElse(null);
                        Long xApproversCount = $$(geth(e, "xApproversCount", Long.class), 0L);
                        for (Route r : report.getSchema().getRoutes()) {
                            xApproversCount = xApproversCount + r.getApprovers().size();

                            for(Approver a: r.getApprovers()) {
                                NoteObject note = new NoteObject();
                                note.setExchange(report);
                                note.setEditor(a.getApprover());
                                note.setType(approve);
                                note.setStatus(waiting);

                                notes.save(note);
                            }

                            for (NoteObject n : report.getNotes()) {
                                String articleValue = n.getStatus().getArticleValue();
                                if ("canceled".equals(articleValue)) {
                                    xApproversCount = -1L;
                                    break;
                                }
                                if ("approved".equals(articleValue)) {
                                    xApproversCount--;
                                }

                            }
                        }
                        e.getIn().setHeader("xApproversCount", xApproversCount);
                    }
                    e.getIn().setHeader("xDataMethodValue", xDataMethodValue);
                    e.getIn().setHeader("xDataDirect", xDataQueue);

                    e.getIn().setBody(request);
                })
                .marshal().json()

                .toD("activemq:${properties:${header.xDataDirect}}?exchangePattern=InOnly")
        ;


    }
}

