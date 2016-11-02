package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.torgammelgard.persistence.entities.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

}
