package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import se.torgammelgard.persistence.entities.TennisSet;

public interface TennisSetRepository extends CrudRepository<TennisSet, Long>{

}
