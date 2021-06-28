package kom3.logbook.integration.direct;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.language.groovy.GroovyLanguage.groovy;

@Component
public class AuthorizationDirect extends RouteBuilder {

    static String TOKEN_PATH= "file:{{report.book.etc}}/${exchangeProperty.system}?fileName=token.xml&noop=true&idempotent=false";

    @Override
    public void configure() {

        from("direct:get-openapi-token")
                .routeId("direct-get-openapi-token")

//                .setProperty("system").simple("${header.system}")
                .setProperty("system").constant("openapi")

                //  read from local
                .doTry()
                .setBody(constant(""))
                .pollEnrich().simple(TOKEN_PATH).timeout(1000)
                .setProperty("tokenKey").xpath("//*[local-name() = 'Token']/text()", String.class)
                .setProperty("tokenExpireTime").xpath("//*[local-name() = 'ExpireTime'][1]", String.class)
                .setProperty("tokenExpireTime").groovy("new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ss.SSSXXX\").parse(exchange.properties.tokenExpireTime)")
                .endDoTry()
                .doCatch(Exception.class)
                .setProperty("tokenKey").constant("")
                .setProperty("tokenExpireTime").groovy("new Date(0)")
                .transform(constant("<empty/>"))
                .end()

                //  if token empty or date expire read from url
                .choice()
                .when(simple("${exchangeProperty.tokenKey} == '' || ${date:now:yyyy-MM-dd'T'HH:mm:ss.SSSXXX} > ${date:exchangeProperty.tokenExpireTime:yyyy-MM-dd'T'HH:mm:ss.SSSXXX}"))
                .setProperty("cfg.openapi.auth.token").simple("{{openapi.auth.token}}")
                .setProperty("cfg.openapi.auth.point").simple("{{openapi.auth.point}}")

                .toD("xslt:file:{{report.book.etc}}/openapi/unpack.xsl")//?transformerFactory=#transformerFactory");
                .removeHeaders("*")
                .toD("${exchangeProperty.cfg.openapi.auth.point}").convertBodyTo(String.class)

                .wireTap(TOKEN_PATH)

                .setProperty("tokenKey").xpath("//*[local-name() = 'Token']/text()", String.class)
                .endChoice().end()

                // set token
                .setProperty("tokenKey").simple("${exchangeProperty.tokenKey}")
                .transform(groovy("[tokenKey: exchange.properties.tokenKey]"))
        ;
    }
}
