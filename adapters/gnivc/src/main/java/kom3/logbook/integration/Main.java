package kom3.logbook.integration;

import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = {"logbook"}, exclude = { // SecurityAutoConfiguration.class,
        WebSocketServletAutoConfiguration.class, AopAutoConfiguration.class, OAuth2ResourceServerAutoConfiguration.class, EmbeddedWebServerFactoryCustomizerAutoConfiguration.class})
@EnableJpaRepositories(basePackages = {"logbook.dbl"})
@EntityScan(basePackages = {"logbook.dbl"})
public class Main {

    @Value("${server.port}")
    String serverPort;

    @Value("${context.path}")
    String contextPath;



//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(check -> {
//            Properties users = new Properties();
//            try {
//                users.load(new FileInputStream(Paths.get(etcPath, "systems.properties").toFile()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (users.containsKey(check))
//                return new User(check, "{noop}" + String.valueOf(users.get(check)), Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
//
//            throw new UsernameNotFoundException("Invalid username");
//        });
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable();
//    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ServletRegistrationBean camelRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), contextPath + "/*");
        servlet.setName("CamelServlet");
        servlet.setLoadOnStartup(1);
        return servlet;
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Component
    class RestApi extends RouteBuilder {

        @Override
        public void configure() {

            restConfiguration()
                    .component("servlet")
                    .port(serverPort)
                    .contextPath(contextPath)
                    .enableCORS(true)
                    .bindingMode(RestBindingMode.json)
                    .dataFormatProperty("prettyPrint", "true")
                    // route-build-open-api
                    .apiContextPath("/open-api")
                    .apiProperty("api.title", "REPORTS-BOOK REST API")
                    .apiProperty("api.version", "v1")
                    .apiProperty("cors", "true") // cross-site
                    .apiContextRouteId("doc-api")
            ;


            rest("/gotonode.svc")
                    .id("service-goto-node")//.description("Teste REST Service")
                    .consumes(MediaType.APPLICATION_JSON)
                    .produces(MediaType.APPLICATION_JSON)
                    .enableCORS(true)

                    .get("/open_api").to("direct:get_OpenApiTokens")
            ;

            rest("/authnode.svc")
                    .id("service-auth-node")//.description("Teste REST Service")
                    .consumes(MediaType.APPLICATION_JSON)
                    .produces(MediaType.APPLICATION_JSON)
                    .enableCORS(true)

                    .get("/{system}/token").to("direct:get-openapi-token")
            ;


            rest("/direct/opn")
                    .id("service-front-console")//.description("Teste REST Service")
                    .consumes(MediaType.APPLICATION_JSON)
                    .produces(MediaType.APPLICATION_JSON)
                    .enableCORS(true)

                    .post("/data/out").bindingMode(RestBindingMode.off).to("direct:opn.data.out")
            ;

        }
    }


}
