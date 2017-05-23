package se.torgammelgard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.User;

/**
 * A Match repo.
 * 
 * @author torgammelgard
 *
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	@Query("select m from Match m where m.owner = ?1")
	public List<Match> findAllBelongingTo(User user);
	
	@Query("select m from Match m where m.id = ?1 and m.owner = ?2")
	public Match findOneForUser(Long id, User user);
}
