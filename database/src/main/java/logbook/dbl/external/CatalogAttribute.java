package logbook.dbl.external;

import logbook.dbl.system.Base;
import logbook.dbl.system.catalog.Catalog;

import javax.persistence.*;
import java.util.UUID;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "EXT_CATALOG_ATTRIBUTE_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "EXT_CATALOG_ATTRIBUTE", uniqueConstraints = {@UniqueConstraint(columnNames = {"UUID"})})
public class CatalogAttribute extends Base {
    //Конструкторы
    public CatalogAttribute(){}

    public CatalogAttribute (String name, String description, Catalog catalog) {
        this.name = name;
        this.description = description;
        this.catalog = catalog;
    }


    //Id записи
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    //Имя столбца
    @Column(name = "NAME", length = 64, nullable = false)
    private String name;


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    //Описание
    @Column(name = "DESCRIPTION", length = 512, nullable = true)
    private String description;

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    //UUID
    @Column(name = "UUID", length = 36, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    // Ссылка на каталог
    @ManyToOne
    @JoinColumn(name = "CATALOG_ID")
    private Catalog catalog;

    public Catalog getCatalog() { return catalog; }

    public void setCatalog(Catalog catalog) { this.catalog = catalog; }
}
