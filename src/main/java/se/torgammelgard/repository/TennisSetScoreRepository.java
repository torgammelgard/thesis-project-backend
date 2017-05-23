package se.torgammelgard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.TennisSetScore;

/**
 * A TennisSetScore repo.
 * 
 * @author torgammelgard
 *
 */
@Repository
public interface TennisSetScoreRepository extends JpaRepository<TennisSetScore, Long> {

}
