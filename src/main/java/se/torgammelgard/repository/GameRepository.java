package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.torgammelgard.persistence.entities.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

}
