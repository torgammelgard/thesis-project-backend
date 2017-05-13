package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.TennisSet;

@Repository
public interface TennisSetRepository extends CrudRepository<TennisSet, Long>{

}
