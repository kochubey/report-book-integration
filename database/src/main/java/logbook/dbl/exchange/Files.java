package logbook.dbl.exchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Files extends CrudRepository<File, Long> {

    public Optional<File> findByUuid(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM INT_EXCHANGE_FILE WHERE EXCHANGE_ID = :exchangeId", nativeQuery = true)
    public List<File> findByExchangeId(@Param("exchangeId") Long exchangeId);

    @Query(value = "SELECT * \n" +
            "FROM INT_EXCHANGE_FILE F\n" +
            "INNER JOIN INT_EXCHANGE E ON E.ID = F.EXCHANGE_ID\n" +
            "WHERE E.UUID = :exchangeUuid AND F.NAME LIKE :fileName ||'.%'\n" +
            "ORDER BY CAST (REPLACE(name, :fileName || '.', '') AS INT) ASC", nativeQuery = true)
    public List<File> partsByExchangeUuidAndName(@Param("exchangeUuid") String exchangeUuid, @Param("fileName") String fileName);

    @Query(value = "SELECT * \n" +
            "FROM INT_EXCHANGE_FILE F\n" +
            "INNER JOIN INT_EXCHANGE E ON E.ID = F.EXCHANGE_ID\n" +
            "WHERE E.UUID = :exchangeUuid AND F.NAME = :fileName \n", nativeQuery = true)
    public List<File> filesByExchangeUuidAndName(@Param("exchangeUuid") String exchangeUuid, @Param("fileName") String fileName);

}
