package logbook.dbl.exchange;

import java.util.Date;

public interface EventProxy {

    public String getId() ;

    public String getUuid() ;

    public String getName() ;

    public Date getStarted() ;

    public Date getFinished();

}
