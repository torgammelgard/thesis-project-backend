package se.torgammelgard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.User;

/**
 * A Team repo.
 * 
 * @author torgammelgard
 *
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("select t from Team t where t.owner = ?1")
	public List<Team> findAllTeamsBelongingTo(User user);
	
	@Query("select t from Team t where t.id = ?1 and t.owner = ?2")
	public Team findOneForUser(Long id, User user);
	
	public long totalCount();
}
