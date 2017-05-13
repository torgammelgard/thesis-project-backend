package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.TennisSetScore;

@Repository
public interface TennisSetScoreRepository extends CrudRepository<TennisSetScore, Long> {

}
