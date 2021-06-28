package logbook.intergation.commons;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class AddProperty implements AggregationStrategy {
    String propertyName;

    public AddProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public Exchange aggregate(Exchange exchange, Exchange in) {
        exchange.setProperty(propertyName, in.getProperty(propertyName));
        return exchange;
    }
}
