package logbook.dbl.exchange.route;

import logbook.dbl.system.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_ROUTE_APPROVER_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_ROUTE_APPROVER")
public class Approver extends Base {
    //    Конструкторы
    public Approver() {

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
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    //  Согласующий
    @ManyToOne
    @JoinColumn(name = "APPROVER_ID")
    private User approver;

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    private Long index;

    public Long getIndex() {
        return index;
    }

    @Column(name = "INDEX", nullable = true)
    public void setIndex(Long index) {
        this.index = index;
    }

    @Column(name = "IS_NOTIFY", nullable = true)
    private Boolean isNotify = false;

    public Boolean getIsNotify() {
        return isNotify;
    }

    public void setIsNotify(Boolean notify) {
        isNotify = notify;
    }
}