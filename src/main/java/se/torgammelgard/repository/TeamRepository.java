package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.torgammelgard.persistence.entities.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

}
