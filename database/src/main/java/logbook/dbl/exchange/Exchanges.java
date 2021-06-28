package logbook.dbl.exchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface Exchanges extends CrudRepository<Exchange, Long> {
    public Optional<Exchange> findByUuid(@Param("uuid") String uuid);

    public List<Exchange> findAllByOrderByIdDesc();

    @Query(value = "" +
            "SELECT E.* FROM INT_EXCHANGE E    \n" +
            "   INNER JOIN SYS_SCHEMA SCH ON SCH.ID = E.SCHEMA_ID \n" +
            "   INNER JOIN SYS_SYSTEM SYS ON SYS.ID = SCH.PROVIDER_ID\n" +
            "   INNER JOIN SYS_DICTIONARY STA ON STA.ARTICLE_VALUE =\n" +
            "(CASE \n" +
            "   WHEN E.STARTED IS NULL AND E.FINISHED IS NULL THEN 'requestGenerated' \n" +
            "   WHEN E.STARTED IS NOT NULL AND E.FINISHED IS NULL THEN 'requestSent' \n" +
            "   WHEN E.STARTED IS NOT NULL AND E.FINISHED IS NOT NULL THEN 'dataReceived' \n" +
            "END)\n" +
            "   WHERE 1=1 \n" +
            "   AND (SYS.ID = :providerId OR :providerId = -1)\n" +
            "   AND (SCH.ID = :schemaId OR :schemaId = -1)\n" +
            "   AND (STA.ID = :statusId OR :statusId = -1)\n" +
            "   AND (COALESCE(E.STARTED, :statedOff) BETWEEN :statedOn AND :statedOff)\n" +
            "   AND (COALESCE(E.FINISHED, :finishedOff) BETWEEN :finishedOn AND :finishedOff)\n"
//            "   AND (COALESCE(E.FINISHED, :toDate) = :toDate)"
            , nativeQuery = true)
    List<Exchange> findFilters(
            @Param("providerId") Long providerId,
            @Param("schemaId") Long schemaId,
            @Param("statusId") Long statusId,
            @Param("statedOn") Date startedOn, @Param("statedOff") Date startedOff,
            @Param("finishedOn") Date finishedOn, @Param("finishedOff") Date finishedff
    );


}


