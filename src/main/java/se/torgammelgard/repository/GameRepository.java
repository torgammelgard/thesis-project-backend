package se.torgammelgard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Game;

/**
 * A dummy Game repo.
 * 
 * @author torgammelgard
 *
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	List<Game> findByVersion(Long version);
}
