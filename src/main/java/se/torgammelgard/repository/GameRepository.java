package se.torgammelgard.repository;

import org.springframework.data.repository.CrudRepository;
import se.torgammelgard.persistence.entities.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

}
