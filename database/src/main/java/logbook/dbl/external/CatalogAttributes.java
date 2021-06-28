package logbook.dbl.external;

import logbook.dbl.system.catalog.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatalogAttributes extends CrudRepository<CatalogAttribute, Long> {
    public List<CatalogAttribute> findByCatalog(@Param("catalog") Catalog catalog);
}
