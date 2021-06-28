package logbook.dbl.exchange.note;

import logbook.dbl.exchange.ExchangeObject;
import logbook.dbl.system.Base;
import logbook.dbl.system.Dictionary;
import logbook.dbl.system.User;

import javax.persistence.*;

//  Системное событие межсистемного обмена
@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "INT_EXCHANGE_NOTE_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "INT_EXCHANGE_NOTE", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class NoteObject extends Base {
    //    Конструкторы
    public NoteObject() { }
    
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

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private Dictionary type;

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
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
    @Column(name = "BODY", columnDefinition = "TEXT")
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private Dictionary status;

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "EDITOR_ID")
    private User editor;

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }
}
