package logbook.dbl.system.catalog;

import logbook.dbl.system.schema.Attribute;
import logbook.dbl.system.schema.Schema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Attributes extends CrudRepository<Attribute, Long> {

    public List<Attribute> findBySchema(@Param("schema") Schema schema);

    @Query(value = "SELECT * FROM SYS_SCHEMA_ATTRIBUTE WHERE SCHEMA_ID = :schemaId", nativeQuery = true)
    public List<Attribute> findBySchemaId(@Param("schemaId") Long schemaId);
}
