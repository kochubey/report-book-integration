package logbook.dbl.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Abonents extends CrudRepository<Abonent, Long> {

    public Optional<Abonent> findByCode(@Param("code") String code);

}
