package logbook.dbl.exchange.note;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteObjects extends CrudRepository<NoteObject, Long> {

}


