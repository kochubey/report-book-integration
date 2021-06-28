package logbook.dbl.exchange.route;

import logbook.dbl.system.*;
import logbook.dbl.system.schema.Schema;

import javax.persistence.*;
import java.util.Set;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_ROUTE_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_ROUTE")
public class Route extends Base {
    //    Конструкторы
    public Route() {

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

    //    Инициатор маршрута для обмена сведениями
    @ManyToOne
    @JoinColumn(name = "INITIATOR_ID", referencedColumnName = "ID")
    private Abonent initiator;

    public Abonent getInitiator() {
        return initiator;
    }

    public void setInitiator(Abonent initiator) {
        this.initiator = initiator;
    }

    //    Инициатор маршрута для обмена сведениями
    @ManyToOne
    @JoinColumn(name = "CONSUMER_ID", referencedColumnName = "ID")
    private Abonent consumer;

    public Abonent getConsumer() {
        return consumer;
    }

    public void setConsumer(Abonent consumer) {
        this.consumer = consumer;
    }

    //  Конечный адрес источника ВС
    @Column(name = "FROM_ENDPOINT", length = 128, nullable = true)
    private String issuer;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    //  Конечный адрес получателя ВС
    @Column(name = "TO_ENDPOINT", length = 128, nullable = true)
    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    //  Конечный адрес получателя ВС
    @Column(name = "EMAIL", length = 128, nullable = true)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //  Целевой формат данных вида сведений
    @ManyToOne
    @JoinColumn(name = "FORMAT_ID", referencedColumnName = "ID")
    private Dictionary format;

    public Dictionary getFormat() {
        return format;
    }

    public void setFormat(Dictionary format) {
        this.format = format;
    }

    @ManyToOne
    @JoinColumn(name = "PROTOCOL_ID", referencedColumnName = "ID")
    private Dictionary protocol;

    public Dictionary getProtocol() {
        return protocol;
    }

    public void setProtocol(Dictionary protocol) {
        this.protocol = protocol;
    }

    @ManyToOne
    @JoinColumn(name = "PERIODICITY_ID")
    private Dictionary pollingPeriodicity;

    public Dictionary getPollingPeriodicity() {
        return pollingPeriodicity;
    }

    public void setPollingPeriodicity(Dictionary pollingPeriodicity) {
        this.pollingPeriodicity = pollingPeriodicity;
    }

    //  Отправка уведомления о доставке
    @Column(name = "IS_CONFIRMING", nullable = true)
    private Boolean isConfirming = false;

    public Boolean getIsConfirming() {
        return isConfirming;
    }

    public void setIsConfirming(Boolean confirming) {
        isConfirming = confirming;
    }

    //  Формирование проткола обработки
    @Column(name = "IS_PROTOCOLING", nullable = true)
    private Boolean isProtocoling = false;

    public Boolean getIsProtocoling() {
        return isProtocoling;
    }

    public void setIsProtocoling(Boolean protocoling) {
        isProtocoling = protocoling;
    }

    //
    @Column(name = "IS_SIGNING", nullable = true)
    private Boolean isSigning = false;

    public Boolean getIsSigning() {
        return isSigning;
    }

    public void setIsSigning(Boolean isSigning) {
        isSigning = isSigning;
    }

    //  Ссылка на сертификат ЭП
    @Column(name = "CERT_LINK", length = 128, nullable = true)
    private String certLink;

    public String getCertLink() {
        return certLink;
    }

    public void setCertLink(String certLink) {
        this.certLink = certLink;
    }

    //  Ссылка xslt-преобразование
    @Column(name = "XSLT_LINK", length = 128, nullable = true)
    private String xsltLink;

    public String getXsltLink() {
        return xsltLink;
    }

    public void setXsltLink(String xsltLink) {
        this.xsltLink = xsltLink;
    }

    // Список согласующих
    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Approver> approvers;

    public Set<Approver> getApprovers() {
        return approvers;
    }

    public void setApprovers(Set<Approver> approvers) {
        this.approvers = approvers;
    }

    // Лист ознакомления
    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Reviewer> reviewers;

    public Set<Reviewer> getReviewers() {
        return reviewers;
    }

    public void setReviewers(Set<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }

    @Column(name = "HAS_TRANSFORMATION", nullable = true)
    private Boolean hasTransformation = false;

    public Boolean getHasTransformation() {
        return hasTransformation;
    }

    public void setHasTransformation(Boolean hasTransformation) {
        this.hasTransformation = hasTransformation;
    }

    @Column(name = "HAS_TARGET_FORMAT", nullable = true)
    private Boolean hasTargetFormat = false;

    public Boolean getHasTargetFormat() {
        return hasTargetFormat;
    }

    public void setHasTargetFormat(Boolean hasTargetFormat) {
        this.hasTargetFormat = hasTargetFormat;
    }

    @ManyToOne
    @JoinColumn(name = "TARGET_FORMAT_ID")
    private Dictionary targetFormat;

    public Dictionary getTargetFormat() {
        return targetFormat;
    }

    public void setTargetFormat(Dictionary targetFormat) {
        this.targetFormat = targetFormat;
    }

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Transformation> transformations;

    public Set<Transformation> getTransformations() {
        return transformations;
    }

    public void setTransformations(Set<Transformation> transformations) {
        this.transformations = transformations;
    }
}