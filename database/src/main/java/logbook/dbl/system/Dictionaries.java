package logbook.dbl.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Dictionaries extends CrudRepository<Dictionary, Long> {
    //
    public List<Dictionary> findAllByCategoryAlias(@Param("categoryAlias") String categoryAlias);

    public Optional<Dictionary> findOneByArticleValue(@Param("articleValue") String articleValue);

    public Optional<Dictionary> findOneByCategoryAliasAndArticleValue(@Param("categoryAlias") String categoryAlias, @Param("articleValue") String articleValue);
}