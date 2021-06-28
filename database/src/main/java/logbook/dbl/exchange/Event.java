package logbook.dbl.exchange;

import logbook.dbl.system.Base;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//  Системное событие межсистемного обмена
@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_EXCHANGE_EVENT_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_EXCHANGE_EVENT", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Event extends Base {
    //    Конструкторы
    public Event() {
        this(null, UUID.randomUUID().toString(), null, null, null, null, null, null, null);
    }

    public Event(String uuid) {
        this(null, uuid, null, null, null, null, null, null, null);
    }

    public Event(ExchangeObject exchangeObject, String uuid, String name, Date started, Date finished, String requestHeaders, String requestBody, String responseHeaders, String responseBody) {
        this.uuid = uuid;
        this.name = name;
        this.started = started;
        this.finished = finished;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.exchange = exchangeObject;
    }

    //  ID записи
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //  UUID события транзакции
    @Column(name = "UUID", nullable = false)
    private String uuid = UUID.randomUUID().toString();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // Наименование события транзакции
    @Column(name = "NAME", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //  Дата начала события транспортной транзакции
    @Column(name = "STARTED", nullable = false)
    private Date started;

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    //  Дата окончания события транспортной транзакции
    @Column(name = "FINISHED", nullable = true)
    private Date finished;

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    //  Заголовки запроса транспортной транзакции
    @Column(name = "REQUEST_HEADERS", columnDefinition = "TEXT")
    private String requestHeaders;

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    //  Тело запроса транспортной транзакции
    @Column(name = "REQUEST_BODY", columnDefinition = "TEXT")
    private String requestBody;

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    //  Заголовки ответа транспортной транзакции
    @Column(name = "RESPONSE_HEADERS", columnDefinition = "TEXT")
    private String responseHeaders;

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    //  Тело ответа транспортной транзакции
    @Column(name = "RESPONSE_BODY", columnDefinition = "TEXT")
    private String responseBody;

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    //  Ссылка на родительскую транспортную транзакцию
    @ManyToOne
    @JoinColumn(name = "EXCHANGE_ID", nullable = true)
    private ExchangeObject exchange;

    public ExchangeObject getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeObject exchange) {
        this.exchange = exchange;
    }
}
