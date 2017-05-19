package se.torgammelgard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("select t from Team t where t.owner = ?1")
	public List<Team> findAllTeamsBelongingTo(User user);
	
	@Query("select t from Team t where ((t.owner = ?1) AND ((t.team1_matches IS NOT EMPTY) OR (t.team2_matches IS NOT EMPTY)))")
	public List<Team> findAllTeamsWithMatches(User owner);
	
	public long totalCount();
}
