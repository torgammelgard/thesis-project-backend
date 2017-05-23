package se.torgammelgard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.TennisSet;

/**
 * A TennisSet repo.
 * 
 * @author torgammelgard
 *
 */
@Repository
public interface TennisSetRepository extends JpaRepository<TennisSet, Long>{

}
