package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import se.torgammelgard.persistence.entities.TennisSetScore;

public interface TennisSetScoreRepository extends CrudRepository<TennisSetScore, Long> {

}
