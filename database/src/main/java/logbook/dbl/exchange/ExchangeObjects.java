package logbook.dbl.exchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeObjects extends CrudRepository<ExchangeObject, Long> {
    public Optional<ExchangeObject> findByUuid(@Param("uuid") String uuid);

    public List<ExchangeObject> findAllByOrderByIdDesc();

    @Query(value = "" +
            "SELECT * FROM INT_EXCHANGE WHERE ID IN (SELECT E.ID FROM INT_EXCHANGE E    \n" +
            "   INNER JOIN SYS_SCHEMA SCH ON SCH.ID = E.SCHEMA_ID \n" +
            "   LEFT JOIN SYS_SYSTEM SYS ON SYS.ID = SCH.PROVIDER_ID\n" +
            "   LEFT JOIN SYS_DICTIONARY STA ON STA.ID = E.STATUS_ID\n" +
            "   LEFT JOIN INT_ROUTE ROU ON SCH.ID = ROU.SCHEMA_ID\n" +
            "   LEFT JOIN SYS_SYSTEM CON ON CON.ID = ROU.CONSUMER_ID\n" +
            "   LEFT JOIN SYS_USER USR ON USR.ID = E.AUTHOR_ID\n" +
            "   LEFT JOIN SYS_DICTIONARY STN ON STN.ARTICLE_VALUE =\n" +
            "(CASE \n" +
            "   WHEN E.STARTED IS NULL AND E.FINISHED IS NULL THEN 'canceled' \n" +
            "   WHEN E.STARTED IS NOT NULL AND E.FINISHED IS NULL THEN 'waiting' \n" +
            "   WHEN E.STARTED IS NOT NULL AND E.FINISHED IS NOT NULL THEN 'approved' \n" +
            "END)\n" +
            "   WHERE 1=1 \n" +
            "   AND (SYS.ID = :providerId OR :providerId = -1)\n" +
            "   AND (SCH.ID = :schemaId OR :schemaId = -1)\n" +
            "   AND (STA.ID = :statusId OR :statusId = -1)\n" +
            "   AND (STN.ID = :noteStatusId OR :noteStatusId = -1)\n" +
            "   AND (CON.ID = :consumerId OR :consumerId = -1)\n" +
            "   AND (USR.ID = :authorId OR :authorId = -1)\n" +
            "   AND (COALESCE(E.STARTED, :statedOff) BETWEEN :statedOn AND :statedOff)\n" +
            "   AND (COALESCE(E.FINISHED, :finishedOff) BETWEEN :finishedOn AND :finishedOff)\n" +
            "   AND (E.CREATED BETWEEN :createdOn AND :createdOff))\n"
//            "   AND (COALESCE(E.FINISHED, :toDate) = :toDate)"
            , nativeQuery = true)
    List<ExchangeObject> findFilters(
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


