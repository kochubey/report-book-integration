package logbook.dbl.exchange.route;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Reviewers extends CrudRepository<Reviewer, Long> {

    @Query(value = "SELECT NEXTVAL  ('INT_ROUTE_REVIEWER_ID_SEQ')", nativeQuery = true)
    Long nextval();

    @Modifying
    @Transactional
    @Query(value = "" +
            "INSERT INTO INT_ROUTE_REVIEWER (ID, CREATED, IS_DELETED," +
            " ROUTE_ID, REVIEWER_ID, IS_NOTIFY)\n" +
            " VALUES (:ID, now(), false,\n" +
            " :ROUTE_ID, :REVIEWER_ID, :IS_NOTIFY)\n"
            , nativeQuery = true)
    void insert(@Param("ID") Long id, @Param("ROUTE_ID") Long routeId, @Param("REVIEWER_ID") Long reviewerId, @Param("IS_NOTIFY") Boolean isNotify);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INT_ROUTE_REVIEWER WHERE ID = :ID", nativeQuery = true)
    void delete(@Param("ID") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INT_ROUTE_REVIEWER WHERE ROUTE_ID = :routeId AND REVIEWER_ID = :reviewerId", nativeQuery = true)
    void deleteByRouteIdAndReviewerId(@Param("routeId") Long routeId, @Param("reviewerId") Long reviewerId);
}
