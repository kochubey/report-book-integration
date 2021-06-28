package logbook.intergation.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.camel.Exchange;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Base {

    public static <T> List<T> asList(Iterable<T> var) {
        return Lists.newArrayList(var);
    }

    public static <T> T getOrDefault(T _object, T _default) {
        if (_object == null) return _default;
        return _object;
    }

    public static <T> T $$(T _object, T _default) {
        if (_object == null) return _default;
        return _object;
    }

    public static <T> T geth(Exchange e, String header$, Class<T> Class) {
        return e.getIn().getHeader(header$, Class);
    }

    ;

    public static void extractHeaders(Exchange e) {
        try {
            Map map = new ObjectMapper().readValue(e.getIn().getBody(String.class), Map.class);
            map.forEach((k, v) -> e.getIn().setHeader(String.valueOf(k), v));
        } catch (IOException ioException) {

        }
        e.getIn().setBody("<o/>");
    }

    public static void extractProperties(Exchange e) {
        e.getProperties().forEach((k, v) -> e.getIn().setHeader(String.valueOf(k), v));
    }
}
