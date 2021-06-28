package logbook.dbl.exchange;

import logbook.dbl.system.Base;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_EXCHANGE_HISTORY_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_EXCHANGE_HISTORY", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class History extends Base {
    //    Конструкторы
    public History() {
        this(null, null);
    }

    public History(ExchangeObject exchangeObject, String action) {
        this.setDeleted(false);
        this.exchange = exchangeObject;
        this.action = action;
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

    //  Ссылка на родительскую транспортную транзакцию
    @ManyToOne
    @JoinColumn(name = "EXCHANGE_ID", nullable = true)
    private ExchangeObject exchange;

    public ExchangeObject getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeObject exchangeObject) {
        this.exchange = exchangeObject;
    }

    //  Тело уведомления
    @Column(name = "ACTION", length = 128)
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
