package kom3.logbook.integration.direct;

import com.google.common.io.ByteStreams;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.UUID;

import static logbook.intergation.commons.Base.getOrDefault;
import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;

@Component
public class EcmNodeDirect extends RouteBuilder {

    @Autowired
    logbook.dbl.exchange.ExchangeObjects reports;

    @Override
    public void configure() {
        onException(Exception.class)
                .toD("direct:onException");


        from("direct:ecm:get:file:byuuid")
                .routeId("direct:ecm:get:file:byuuid")
                .setHeader("cfg.etc.catalog").simple("{{cfg.etc.catalog}}")
                .process(e -> {
                    String dataDir = e.getIn().getHeader("cfg.etc.catalog", String.class);
                    String uuidDir = e.getIn().getHeader("uuid", String.class);


                    Properties readme = new Properties();
                    if (Paths.get(dataDir, uuidDir, "readme").toFile().exists())
                        readme.load(new BufferedInputStream(new FileInputStream(Paths.get(dataDir, uuidDir, "readme").toString())));


                    e.getOut().setHeader("Content-Type", "application/octet-stream");
                    e.getOut().setHeader("Content-Disposition", "attachment; filename=\"" + readme.get("filename") + "\"");

                    byte[] bytes = null;
                    if (Paths.get(dataDir, uuidDir, "body").toFile().exists()) {
                        bytes = Files.readAllBytes(Paths.get(dataDir, uuidDir, "body"));
                    } else {
                        e.setProperty(HTTP_RESPONSE_CODE, constant(403));
                        bytes = new byte[]{};
                    }
                    e.getOut().setBody(bytes, ByteArrayOutputStream.class);
                })
        ;

        from("direct:ecm:post:new:file")
                .routeId("direct:ecm:post:new:file")

                .setHeader("xExchTS").groovy("new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ss\").format(new Date())")
                .setHeader("cfg.etc.catalog").simple("{{cfg.etc.catalog}}")

                .process(e -> {
                    String reportId = e.getIn().getHeader("reportId", String.class);
                    String reportUUID = e.getIn().getHeader("reportUUID", String.class);

                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    String disposition = getOrDefault(e.getIn().getHeader("Content-Disposition", String.class), "filename=\"body\"");
                    String fileName = disposition.replaceFirst("^.*filename=\"?([^\"]+)\"?.*$", "$1").trim();

                    ByteArrayInputStream body = e.getIn().getBody(ByteArrayInputStream.class);

                    String dataDir = e.getIn().getHeader("cfg.etc.catalog", String.class);

                    Files.createDirectories(Paths.get(dataDir, uuid));
                    byte[] bytes = ByteStreams.toByteArray(body);
                    Files.write(Paths.get(dataDir, uuid, "body"), bytes);

                    StringBuilder readme = new StringBuilder();
                    if (fileName != null) {
                        readme.append("filename=").append(fileName).append("\n");
                        e.getIn().setHeader("fileName", fileName);
                        e.getIn().setHeader("fileSize", bytes.length);
                    }
                    if (reportId != null && !reportId.isEmpty()) {
                        readme.append("reportid=").append(reportId).append("\n");
                        e.getIn().setHeader("parentId", reportId);
                    }
                    if (reportUUID != null && !reportUUID.isEmpty()) {
                        readme.append("reportuuid=").append(reportUUID).append("\n");
                        e.getIn().setHeader("parentUUID", reportUUID);
                    }

                    Files.write(Paths.get(dataDir, uuid, "readme"), readme.toString().getBytes());

                    e.getIn().setHeader("xExchID", uuid);
                    e.getIn().setBody("<o/>");
                })
                .setHeader("Content-Type", constant("application/json"))
                .toD("xslt:file:{{report.book.etc}}/ecmnode/unpack.xsl")
        ;

        from("direct:ecm:post:file:byuuid")
                .routeId("direct:ecm:post:file:byuuid")
                .setHeader("xExchTS").groovy("new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ss\").format(new Date())")
                .setHeader("cfg.etc.catalog").simple("{{cfg.etc.catalog}}")
                .process(e -> {
                    String uuidDir = e.getIn().getHeader("uuid", String.class);

                    String reportId = e.getIn().getHeader("reportId", String.class);
                    String reportUUID = e.getIn().getHeader("reportUUID", String.class);

                    String disposition = getOrDefault(e.getIn().getHeader("Content-Disposition", String.class), "filename=\"body\"");
                    String fileName = disposition.replaceFirst("^.*filename=\"?([^\"]+)\"?.*$", "$1").trim();

                    ByteArrayInputStream body = e.getIn().getBody(ByteArrayInputStream.class);

                    String dataDir = e.getIn().getHeader("cfg.etc.catalog", String.class);

                    Files.createDirectories(Paths.get(dataDir, uuidDir));
                    byte[] bytes = ByteStreams.toByteArray(body);
                    Files.write(Paths.get(dataDir, uuidDir, "body"), bytes, StandardOpenOption.CREATE,
                            StandardOpenOption.TRUNCATE_EXISTING);


                    StringBuilder readme = new StringBuilder();
                    if (fileName != null) {
                        readme.append("filename=").append(fileName).append("\n");
                        e.getIn().setHeader("fileName", fileName);
                        e.getIn().setHeader("fileSize", bytes.length);
                    }
                    if (reportId != null && !reportId.isEmpty()) {
                        readme.append("reportid=").append(reportId).append("\n");
                        e.getIn().setHeader("parentId", reportId);
                    }
                    if (reportUUID != null && !reportUUID.isEmpty()) {
                        readme.append("reportuuid=").append(reportUUID).append("\n");
                        e.getIn().setHeader("parentUUID", reportUUID);
                    }

                    Files.write(Paths.get(dataDir, uuidDir, "readme"), readme.toString().getBytes(), StandardOpenOption.CREATE,
                            StandardOpenOption.TRUNCATE_EXISTING);

                    e.getIn().setHeader("xExchID", uuidDir);
                    e.getIn().setBody("<o/>");
                })
                .setHeader("Content-Type", constant("application/json"))
                .toD("xslt:file:{{report.book.etc}}/ecmnode/unpack.xsl")
        ;
    }
}

