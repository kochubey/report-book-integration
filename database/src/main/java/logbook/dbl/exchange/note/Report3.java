package logbook.dbl.exchange.note;

import java.util.Date;

public interface Report3 {

    public Long getId();

    public Date getCreated();

    public Long getEditorId();

    public String getEditorVal();

    public Long getAvgInterval();

    public Long getSumInterval();
}