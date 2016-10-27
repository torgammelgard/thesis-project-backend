package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import se.torgammelgard.persistence.entities.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

}
