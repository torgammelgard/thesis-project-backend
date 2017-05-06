package se.torgammelgard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.persistence.entities.Game;

@Service
@Transactional
public interface GameService {

    public List<Game> findAll();

    public Game save(Game game);

    public void remove(Game game);
}
