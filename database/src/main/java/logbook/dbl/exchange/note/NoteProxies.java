package logbook.dbl.exchange.note;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NoteProxies extends CrudRepository<NoteProxy, Long> {

    @Query(value = "" +
            "SELECT N.*, 0 AS AVG_INTERVAL, 0 AS SUM_INTERVAL FROM INT_EXCHANGE_NOTE_VIEW N \n" +
            "   WHERE 1=1 \n" +
            "   AND (N.TYPE_ID = :typeId OR :typeId = -1) \n" +
            "   AND (N.EDITOR_ID = :editorId OR :editorId = -1)"
            , nativeQuery = true)
    public List<NoteProxy> findByEditorId(
            @Param("typeId") Long typeId,
            @Param("editorId") Long editorId
            );

    @Query(value = "" +
            "SELECT V.*, U.LAST_NAME || ' ' || LEFT(U.FIRST_NAME,1) || '.' AS editorVal FROM (SELECT  " +
            "   ROW_NUMBER() OVER() AS id, " +
            "   NOW() AS created, " +
            "   EDITOR_ID AS editorId, " +
            "   CAST(AVG(APPROVING_INTERVAL) AS INTEGER) AS avgInterval, " +
            "   SUM(APPROVING_INTERVAL) AS sumInterval \n" +
            "FROM INT_EXCHANGE_NOTE_VIEW\n" +
            "WHERE \n" +
            " 1=1\n" +
            "   AND (SCHEMA_ID = :schemaId OR -1= :schemaId) \n" +
            "   AND (EDITOR_ID = :editorId OR -1= :editorId) \n" +
            "   AND (CREATED BETWEEN :createdOn AND :createdOff) \n" +
            "GROUP BY EDITOR_ID) V \n" +
            "INNER JOIN SYS_USER U ON U.ID = V.editorId \n"
            , nativeQuery = true)
    public List<Report3> report3(
            @Param("schemaId") Long schemaId,
            @Param("editorId") Long editorId,
            @Param("createdOn") Date createdOn, @Param("createdOff") Date createdOff
            );

}


