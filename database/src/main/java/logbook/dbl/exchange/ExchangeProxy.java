package logbook.dbl.exchange;

import logbook.dbl.system.Base;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "INT_EXCHANGE_VIEW")
@Immutable
@Getter @Setter
public class ExchangeProxy extends Base {
    //    Конструкторы
    public ExchangeProxy() {
    }

    //  ID записи
    @Id
    private Long id;

    private String uuid;

    private String lid;

    private Date started;

    private Date finished;

    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "TO_DATE")
    private Date toDate;

    @Column(name = "SYSTEM_ID")
    private Long systemId;

    @Column(name = "SYSTEM_VAL")
    private String systemVal;

    @Column(name = "SCHEMA_ID")
    private Long schemaId;

    @Column(name = "SCHEMA_VAL")
    private String schemaVal;

    @Column(name = "STATUS_ID")
    private Long statusId;

    @Column(name = "STATUS_VAL")
    private String statusVal;

    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @Column(name = "AUTHOR_VAL")
    private String authorVal;

}