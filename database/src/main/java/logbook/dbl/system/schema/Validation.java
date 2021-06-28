package logbook.dbl.system.schema;

import logbook.dbl.system.Base;
import logbook.dbl.system.Dictionary;

import javax.persistence.*;
import java.util.UUID;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SYS_SCHEMA_VALIDATION_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "SYS_SCHEMA_VALIDATION")
public class Validation extends Base {
    //    Конструкторы
    public Validation() {
    }

    public Validation(Schema schema, String code, String field, String rule) {
        this.schema = schema;
        this.code = code;
        this.field = field;
        this.rule = rule;
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

    //  Значение идентификатора
    @Column(name = "CODE", length = 16, nullable = false, updatable = false)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //  Тип
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    private Dictionary type;

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }

    @Column(name = "FIELD", length = 16, nullable = false)
    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Column(name = "RULE", columnDefinition = "TEXT")
    private String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = true;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Column(name = "SHARE", nullable = false)
    private Long share = 100L;

    public Long getShare() {
        return share;
    }

    public void setShare(Long share) {
        this.share = share;
    }

    @Column(name = "CRITICALITY", nullable = false)
    private Float criticality = 1F;

    public Float getCriticality() {
        return criticality;
    }

    public void setCriticality(Float criticality) {
        this.criticality = criticality;
    }
}