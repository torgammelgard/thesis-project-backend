package se.torgammelgard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.User;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	@Query("select m from Match m where m.owner = ?1")
	public List<Match> findAllBelongingTo(User user);
}
