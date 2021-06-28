package logbook.dbl.exchange.route;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Transformations extends CrudRepository<Transformation, Long> {

//    public Set<Transformation> findByRoute(@Param("route") Route route);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INT_ROUTE_TRANSFORMATION WHERE ROUTE_ID = :routeId", nativeQuery = true)
    void deleteByRouteId(@Param("routeId") Long routeId);

//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM SYS_SCHEMA_VALIDATION WHERE UUID = :uuid", nativeQuery = true)
//    void deleteByUuid(@Param("uuid") String uuid);

}
