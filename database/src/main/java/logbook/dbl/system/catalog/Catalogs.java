package logbook.dbl.system.catalog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Catalogs extends CrudRepository<Catalog, Long> {
}
