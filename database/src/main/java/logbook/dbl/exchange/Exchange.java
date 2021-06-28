package logbook.dbl.exchange;

import logbook.dbl.system.Base;

import logbook.dbl.system.User;
import logbook.dbl.system.schema.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_EXCHANGE_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_EXCHANGE")
public class Exchange extends Base {
    //    Конструкторы
    public Exchange(Schema schema, User user, Date started, Object finished, Object fromDate, Object toDate) {
        this(null, null, null, null, null, null);
    }


    public Exchange(Schema schema, User author, Date started, Date finished, Date fromDate, Date toDate) {
        this.schema = schema;
        this.author = author;
        this.started = started;
        this.finished = finished;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    //  UUID транзакции
    @Column(name = "UUID", length = 36, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    //  GID транзакции
    @Column(name = "LID", nullable = true)
    private String lid;

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    //  Вид сведений
    @ManyToOne
    @JoinColumn(name = "SCHEMA_ID")
    private Schema schema;

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    //  Инициатор запроса/отчета
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    //  Дата начала транспортной транзакции
    @Column(name = "STARTED", nullable = true)
    private Date started;

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    //  Дата окончания транспортной транзакции
    @Column(name = "FINISHED", nullable = true)
    private Date finished;

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    //
    @Column(name = "FROM_DATE", nullable = true)
    private Date fromDate;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    //
    @Column(name = "TO_DATE", nullable = true)
    private Date toDate;

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    // Список файлов, полученных в рамках транспортной транзакции
    @OneToMany(mappedBy = "exchange", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<File> files;

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    // История
    @OneToMany(mappedBy = "exchange", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<History> histories;

    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }

    // Список событий транспортной транзакции
    @OneToMany(mappedBy = "exchange", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Event> events;

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

}