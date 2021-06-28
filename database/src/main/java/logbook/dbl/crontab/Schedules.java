package logbook.dbl.crontab;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface Schedules extends CrudRepository<Schedule, Long> {

    @Query(value = "SELECT NEXTVAL ('SCH_SCHEDULE_ID_SEQ')", nativeQuery = true)
    Long nextval();

    List<Schedules> findAllByOrderByCreatedDesc();

    @Modifying
    @Transactional
    @Query(value = "" +
            "INSERT INTO SCH_SCHEDULE (ID, CREATED, IS_DELETED, UPDATED, DELAY, IS_PROCESSED, REPORT_DEPTH, AUTHOR_ID, PERIODICITY_ID, SCHEMA_ID)\n" +
            "   VALUES (:ID, now(), false, now(), :DELAY, true, :REPORTDEPTH, :AUTHORID, :PERIODICITYID, :SCHEMAID)\n"
            , nativeQuery = true)
    void insert(@Param("ID") Long id, @Param("SCHEMAID") Long schemaId, @Param("PERIODICITYID") Long periodicityId, @Param("AUTHORID") Long authorId,
                @Param("DELAY") Long delay, @Param("REPORTDEPTH") Long reportDepth);

    @Modifying
    @Transactional
    @Query(value = "" +
            "UPDATE SCH_SCHEDULE SET \n" +
            "   IS_PROCESSED = FALSE, \n" +
            "   DELAY = :DELAY, \n" +
            "   REPORT_DEPTH = :REPORTDEPTH, \n" +
            "   AUTHOR_ID = :AUTHORID, \n" +
            "   PERIODICITY_ID = :PERIODICITYID, \n" +
            "   SCHEMA_ID = :SCHEMAID  \n" +
            "WHERE ID = :ID"
            , nativeQuery = true)
    void update(@Param("ID") Long id, @Param("SCHEMAID") Long schemaId, @Param("PERIODICITYID") Long periodicityId, @Param("AUTHORID") Long authorId, @Param("DELAY") Long delay, @Param("REPORTDEPTH") Long reportDepth);

}