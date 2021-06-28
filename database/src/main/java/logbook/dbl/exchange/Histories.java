package logbook.dbl.exchange;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Histories extends CrudRepository<History, Long> {
}


