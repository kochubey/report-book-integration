package logbook.dbl.system.schema;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface Validations extends CrudRepository<Validation, Long> {

    public Set<Validation> findBySchema(@Param("schema") Schema schema);

    @Modifying 
    @Transactional
    @Query(value = "DELETE FROM SYS_SCHEMA_VALIDATION WHERE ID = :ID", nativeQuery = true)
    void delete(@Param("ID") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SYS_SCHEMA_VALIDATION WHERE UUID = :uuid", nativeQuery = true)
    void deleteByUuid(@Param("uuid") String uuid);

}
