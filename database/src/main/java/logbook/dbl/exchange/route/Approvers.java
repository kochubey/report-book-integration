package logbook.dbl.exchange.route;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Approvers extends CrudRepository<Approver, Long> {

    @Query(value = "SELECT NEXTVAL ('INT_ROUTE_APPROVER_ID_SEQ')", nativeQuery = true)
    Long nextval();

    @Modifying
    @Transactional
    @Query(value = "" +
            "INSERT INTO INT_ROUTE_APPROVER (ID, CREATED, IS_DELETED," +
            " ROUTE_ID, APPROVER_ID, INDEX, IS_NOTIFY)\n" +
            " VALUES (:ID, now(), false,\n" +
            " :ROUTE_ID, :APPROVER_ID, :INDEX, :IS_NOTIFY)\n"
            , nativeQuery = true)
    void insert(@Param("ID") Long id, @Param("ROUTE_ID") Long routeId, @Param("APPROVER_ID") Long approver, @Param("INDEX") Long index, @Param("IS_NOTIFY") Boolean isNotify);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INT_ROUTE_APPROVER WHERE ROUTE_ID = :routeId AND INDEX = :index", nativeQuery = true)
    void deleteByRouteIdAndIndex(@Param("routeId") Long routeId, @Param("index") Long index);
}
