package logbook.dbl.exchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExchangeProxies extends CrudRepository<ExchangeProxy, Long> {

    @Query(value = "SELECT * FROM INT_EXCHANGE_VIEW E\n" +
            "   WHERE 1=1 \n" +
            "   AND (SYSTEM_ID = :providerId OR :providerId = -1)\n" +
            "   AND (SCHEMA_ID = :schemaId OR :schemaId = -1)\n" +
            "   AND (STATUS_ID = :statusId OR :statusId = -1)\n" +
            "   AND (NOTE_STATUS_ID = :noteStatusId OR :noteStatusId = -1)\n" +
            "   AND (CONSUMER_ID = :consumerId OR :consumerId = -1)\n" +
            "   AND (AUTHOR_ID = :authorId OR :authorId = -1)\n" +
            "   AND (COALESCE(E.STARTED, :statedOff) BETWEEN :statedOn AND :statedOff)\n" +
            "   AND (COALESCE(E.FINISHED, :finishedOff) BETWEEN :finishedOn AND :finishedOff)\n" +
            "   AND (E.CREATED BETWEEN :createdOn AND :createdOff) \n"
            , nativeQuery = true)
    List<ExchangeProxy> findFilters(
            @Param("providerId") Long providerId,
            @Param("schemaId") Long schemaId,
            @Param("statusId") Long statusId,
            @Param("statedOn") Date startedOn, @Param("statedOff") Date startedOff,
            @Param("finishedOn") Date finishedOn, @Param("finishedOff") Date finishedff,
            @Param("createdOn") Date createdOn, @Param("createdOff") Date createdOff,
            @Param("authorId") Long authorId,
            @Param("consumerId") Long consumerId,
            @Param("noteStatusId") Long noteStatusId
    );

}


