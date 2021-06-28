package logbook.dbl.system.schema;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Schemas extends CrudRepository<Schema, Long> {
//   todo нужно будет закрыть поиск конкретного ВС только по коду, либо
    public Optional<Schema> findByCode(@Param("code") String code);

    public Optional<Schema> findByNamespace(@Param("namespace") String namespace);
}
