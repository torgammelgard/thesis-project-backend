package se.torgammelgard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
