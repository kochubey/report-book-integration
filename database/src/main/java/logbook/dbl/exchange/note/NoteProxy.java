package logbook.dbl.exchange.note;

import logbook.dbl.system.Base;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "INT_EXCHANGE_NOTE_VIEW")
@Immutable
@Getter @Setter
public class NoteProxy extends Base {

    public NoteProxy() {
    }

    @Column(name = "EXCHANGE_ID")
    private Long exchangeId;

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE_ID")
    private Long typeId;

    @Column(name = "TYPE_VAL")
    private String typeVal;

    @Column(name = "STATUS_ID")
    private Long statusId;

    @Column(name = "STATUS_VAL")
    private String statusVal;

    @Column(name = "EDITOR_ID")
    private Long editorId;

    @Column(name = "EDITOR_VAL")
    private String editorVal;

    @Column(name = "SCHEMA_ID")
    private Long schemaId;

    @Column(name = "SCHEMA_VAL")
    private String schemaVal;

    @Column(name = "BODY")
    private String body;

    @Column(name = "APPROVING_INTERVAL")
    private Long approvingInterval;

}
