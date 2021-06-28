package logbook.dbl.system;

import logbook.dbl.system.schema.Schema;

import javax.persistence.*;
import java.util.Set;

// Система (абонент), участвующая в рамках обмена видами сведений через интеграционную платформу

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SYS_SYSTEM_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "SYS_SYSTEM", uniqueConstraints = {@UniqueConstraint(columnNames = {"CODE"})})
//@JsonPropertyOrder({"id", "code", "name", "description", "created", "updated"})
public class Abonent extends Base {
    //    Конструкторы
    public Abonent() { this(null, null ,null, null);
    }

    public Abonent(String code, String name, String description, String endpoint) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.endpoint = endpoint;
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

    //  Значение идентификатора
    @Column(name = "CODE", length = 16, nullable = false, updatable = false)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //  Наименование
    @Column(name = "NAME", length = 64, nullable = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //  Назначение
    @Column(name = "DESCRIPTION", length = 256, nullable = true)
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // Список событий транспортной транзакции
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Schema> schemas;

    public Set<Schema> getSchemas() {
        return schemas;
    }

    public void setSchemas(Set<Schema> schemas) {
        this.schemas = schemas;
    }

    //  Точка доступа
    @Column(name = "ENDPOINT", length = 128, nullable = true)
    private String endpoint="not_determined";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    //  URN пространство имен реестра абонентов
    @Transient
    private String namespace;

    public String getNamespace() {
        return String.format("urn://system/%s", code).toLowerCase();
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}