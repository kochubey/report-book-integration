package logbook.dbl.exchange;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface Events extends CrudRepository<Event, Long> {

    public Optional<Event> findByUuid(@Param("uuid") String uuid);

    @Override
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    <S extends Event> S save(S s);


    @Query(value = "" +
            "SELECT ID, UUID, NAME, STARTED, FINISHED FROM INT_EXCHANGE_EVENT\n" +
            "WHERE \n" +
            "   1=1\n" +
            "   AND (EXCHANGE_ID = :exchangeId OR -1= :exchangeId) \n" +
            "ORDER BY STARTED DESC"
            , nativeQuery = true)
    public List<EventProxy> findByExchangeId(@Param("exchangeId") Long exchangeId);


}
