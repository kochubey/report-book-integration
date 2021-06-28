package kom3.logbook.integration.config;

import org.apache.camel.component.activemq.ActiveMQComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMQComponentConfig {

    @Bean(name = "activemq")
    public ActiveMQComponent createComponent(ConnectionFactory factory) {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConnectionFactory(factory);
        return activeMQComponent;
    }

}
