package logbook.dbl.system.schema;

import logbook.dbl.system.Base;

import javax.persistence.*;
import java.util.UUID;

// schema attribute

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SYS_SCHEMA_ATTRIBUTE_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "SYS_SCHEMA_ATTRIBUTE", uniqueConstraints = {@UniqueConstraint(columnNames = {"UUID"})})
public class Attribute extends Base {
    //Конструкторы

    public Attribute() {

    }

    public Attribute( String name, Schema schema) {

        this.name = name;
        this.schema = schema;
    }

    //Id записи
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    public Long getId() { return id; }

    public void setId(Long id) {this.id = id;}


    //Name
    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    //UUID
    @Column(name = "UUID", length = 36, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

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
}
