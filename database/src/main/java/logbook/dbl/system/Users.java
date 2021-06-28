package logbook.dbl.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Users extends CrudRepository<User, Long> {
    public Optional<User> findByLogin(@Param("login") String login);
}
