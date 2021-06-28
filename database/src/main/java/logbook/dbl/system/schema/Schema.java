package logbook.dbl.system.schema;

import logbook.dbl.crontab.Schedule;
import logbook.dbl.exchange.route.Route;
import logbook.dbl.system.Abonent;
import logbook.dbl.system.Base;
import logbook.dbl.system.Dictionary;

import javax.persistence.*;
import java.util.Set;

//Вид сведений

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SYS_SCHEMA_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "SYS_SCHEMA", uniqueConstraints = {@UniqueConstraint(columnNames = {"PROVIDER_ID", "CODE", "VERSION"})})
//@JsonPropertyOrder({"id", "sourceId", "code", "version", "name", "description", "created", "updated"})
public class Schema extends Base {

    //    Конструкторы
    public Schema() {
        this(null, null, null, null, null);
    }

    public Schema(Abonent provider, String code, String version, String name, String description) {
        this.provider = provider;
        this.code = code;
        this.version = version;
        this.name = name;
        this.description = description;
    }

    //    ID записи
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

    //    Поставщик видов сведений
    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "ID")
    private Abonent provider;

    public Abonent getProvider() {
        return provider;
    }

    public void setProvider(Abonent provider) {
        this.provider = provider;
    }

    //  Код вида сведений
    @Column(name = "CODE", length = 16, nullable = false)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //    Версия вида сведений
    @Column(name = "VERSION", length = 16, nullable = false)
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    //    Наименование вида сведений
    @Column(name = "NAME", length = 256, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    Описание вида сведений
    @Column(name = "DESCRIPTION", length = 512, nullable = true)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Формат данных вида сведений
    @ManyToOne
    @JoinColumn(name = "FORMAT_ID", referencedColumnName = "ID", nullable = true)
    private Dictionary format;

    public Dictionary getFormat() {
        return format;
    }

    public void setFormat(Dictionary format) {
        this.format = format;
    }

    // Ссылка на схему данных
    @Column(name = "LOCATION", length = 512, nullable = true)
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Список файлов, полученных в рамках транспортной транзакции
    @OneToMany(mappedBy = "schema", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Validation> validations;

    public Set<Validation> getValidations() {
        return validations;
    }

    public void setValidations(Set<Validation> validations) {
        this.validations = validations;
    }

    // Маршрут
    @OneToMany(mappedBy = "schema", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Route> routes;

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    // Расписания по ВС
    @OneToMany(mappedBy = "schema", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Schedule> schedules;

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    @OneToMany(mappedBy = "schema", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Attribute> attributes;

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Column(name = "NAMESPACE", length = 128, nullable = true)
    private String namespace;

    public String getNamespace() {
        return (namespace != null && ! namespace.isEmpty()) ? namespace : String.format("urn://schema/%s/%s/%s", provider.getCode(), code, version).toLowerCase();
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

}