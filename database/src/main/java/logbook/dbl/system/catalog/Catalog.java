package logbook.dbl.system.catalog;

import logbook.dbl.system.Base;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

// schema_dictionary

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SYS_SCHEMA_DICTIONARY_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "EXT_CATALOG", uniqueConstraints = {@UniqueConstraint(columnNames = {"UUID"})})
public class Catalog extends Base {
    //Конструкторы

    public  Catalog(){
        this(null, null);
    }

    public Catalog(String name, String articleValue) {
        this.name = name;
        this.articleValue = articleValue;
    }

    //Id записи
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    //Name
    @Column(name = "NAME", unique = true, length = 64, nullable = false)
    private String articleValue;

    public String getArticleValue() {
        return articleValue;
    }

    public void setArticleValue(String articleValue) {
        this.articleValue = articleValue;
    }

    @Column(name = "FULL_NAME", unique = true, length = 64, nullable = false)
    private String name;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }


    //UUID
    @Column(name = "UUID", length = 36, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    @OneToMany(mappedBy = "catalog", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Attribute> attributes;

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }
}