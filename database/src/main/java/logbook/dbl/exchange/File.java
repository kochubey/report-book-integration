package logbook.dbl.exchange;

import logbook.dbl.system.Base;

import javax.persistence.*;

//  Файлы транспортной транзакции

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_EXCHANGE_FILE_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_EXCHANGE_FILE", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
//@JsonPropertyOrder({"id"})
public class File extends Base {
    //    Конструкторы
    public File() {
        this(null, null, null, null);
    }

    public File(ExchangeObject exchangeObject, String uuid, String name, Long size) {
        this.exchange = exchangeObject;
        this.uuid = uuid;
        this.name = name;
        this.size = size;
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
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    //  GID
    @Column(name = "GID", nullable = true)
    private String gid;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    // Наименование файла
    @Column(name = "NAME", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String eventName) {
        this.name = name;
    }

    // Наименование файла
    @Column(name = "SIZE", nullable = true)
    private Long size;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    //  Ссылка на родительскую транспортную транзакцию
    @ManyToOne
    @JoinColumn(name = "EXCHANGE_ID", nullable = true)
    private ExchangeObject exchange;

    public ExchangeObject getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeObject exchangeObject) {
        this.exchange = exchangeObject;
    }

    @Transient
    public Long getCreatedTS() {
        return getCreated().getTime();
    }
}
