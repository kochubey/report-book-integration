package logbook.dbl.crontab;

import logbook.dbl.system.Base;
import logbook.dbl.system.Dictionary;
import logbook.dbl.system.schema.Schema;
import logbook.dbl.system.User;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SCH_SCHEDULE_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "SCH_SCHEDULE", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID", "SCHEMA_ID"})})
public class Schedule extends Base {

    public Schedule() {
        this(null, null, null, null, null, null);
    }

    public Schedule(Schema schema, User author, Dictionary periodicity) {
        this(schema, author, periodicity, null, null, null);
    }

    public Schedule(Schema schema, User author, Dictionary periodicity, Long delay, Long reportDepth, Boolean isProcessed) {
        this.schema = schema;
        this.author = author;
        this.periodicity = periodicity;
        this.delay = delay;
        this.reportDepth = reportDepth;
        this.isProcessed = isProcessed;
    }

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

    @ManyToOne
    @JoinColumn(name = "SCHEMA_ID")
    private Schema schema;

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "PERIODICITY_ID")
    private Dictionary periodicity;

    public Dictionary getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Dictionary periodicity) {
        this.periodicity = periodicity;
    }

    @Column(name = "DELAY", nullable = false)
    private Long delay;

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    @Column(name = "REPORT_DEPTH", nullable = false)
    private Long reportDepth;

    public Long getReportDepth() {
        return reportDepth;
    }

    public void setReportDepth(Long reportDepth) {
        this.reportDepth = reportDepth;
    }

    @Column(name = "IS_PROCESSED", nullable = false, columnDefinition = "boolean default false")
    private Boolean isProcessed = false;

    public Boolean getProcessed() {
        return isProcessed;
    }

    public void setProcessed(Boolean processed) {
        isProcessed = processed;
    }

}