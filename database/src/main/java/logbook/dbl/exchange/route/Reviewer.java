package logbook.dbl.exchange.route;

import logbook.dbl.system.Base;
import logbook.dbl.system.User;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_ROUTE_REVIEWER_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_ROUTE_REVIEWER")
public class Reviewer extends Base {
    //    Конструкторы
    public Reviewer() {
        this(null, null);
    }

    public Reviewer(Route route, User reviewer) {
        this.route = route;
        this.reviewer = reviewer;
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
    @JoinColumn(name = "REVIEWER_ID")
    private User reviewer;

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
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