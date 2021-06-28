package logbook.dbl.exchange.route;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Routes extends CrudRepository<Route, Long> {
    @Query(value = "SELECT NEXTVAL ('INT_ROUTE_ID_SEQ')", nativeQuery = true)
    Long nextval();

    @Modifying
    @Transactional
    @Query(value = "" +
            "INSERT INTO INT_ROUTE (ID, CREATED, IS_DELETED," +
            " ROUTE_ID, REVIEWER_ID)\n" +
            " VALUES (:ID, now(), false,\n" +
            " :ROUTE_ID, :REVIEWER_ID)\n"
            , nativeQuery = true)
    void insert(@Param("ID") Long id, @Param("ROUTE_ID") Long routeId, @Param("REVIEWER_ID") Long reviewerId);


    @Modifying
    @Transactional
    @Query(value = "" +
            "UPDATE INT_ROUTE SET \n" +
            "SCHEMA_ID = :dataTypeId,\n" +
            "CONSUMER_ID = :consumerId,\n" +
            "INITIATOR_ID = :initiatorId,\n" +
            "PERIODICITY_ID = :pollingPeriodicityId,\n" +
            "PROTOCOL_ID = :protocolId,\n" +
            "IS_CONFIRMING = :isConfirming,\n" +
            "IS_PROTOCOLING = :isProtocoling,\n" +
            "FROM_ENDPOINT = :systemEndpoint,\n" +
            "TO_ENDPOINT = :consumerEndpoint,\n" +
            "HAS_TRANSFORMATION = :hasTransformation,\n" +
            "HAS_TARGET_FORMAT = :hasTargetFormat,\n" +
            "TARGET_FORMAT_ID = :targetFormatId\n" +
            "WHERE ID = :id"
            , nativeQuery = true)
    void update(@Param("id") Long id,
                @Param("dataTypeId") Long dataTypeId, @Param("initiatorId") Long initiatorId,
                @Param("consumerId") Long consumerId, @Param("protocolId") Long protocolId,
                @Param("pollingPeriodicityId") Long pollingPeriodicityId,
                @Param("isConfirming") boolean isConfirming, @Param("isProtocoling") Boolean isProtocoling,
                @Param("systemEndpoint") String systemEndpoint, @Param("consumerEndpoint") String consumerEndpoint,
                @Param("hasTransformation") Boolean hasTransformation,
                @Param("hasTargetFormat") Boolean hasTargetFormat, @Param("targetFormatId") Long targetFormatId
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INT_ROUTE_REVIEWER WHERE ID = :ID", nativeQuery = true)
    void delete(@Param("ID") Long id);


}
