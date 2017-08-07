package falcon.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by tibor on 2017-08-07.
 */
@Repository
public interface RfcRepository extends CrudRepository<TranslatedRFC, String> {

    Optional<TranslatedRFC> findById(String id);

}
