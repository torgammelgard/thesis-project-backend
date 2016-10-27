package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import se.torgammelgard.persistence.entities.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

}
