package se.torgammelgard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
