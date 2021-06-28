package logbook.dbl.exchange.route;

import logbook.dbl.system.Base;

import javax.persistence.*;
import java.util.UUID;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_ROUTE_TRANSFORMATION_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_ROUTE_TRANSFORMATION")
public class Transformation extends Base {
    //    Конструкторы
    public Transformation() {
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
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Column(name = "RULE", columnDefinition = "TEXT")
    private String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

}