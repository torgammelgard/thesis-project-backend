package se.torgammelgard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.torgammelgard.persistence.entities.Game;
import se.torgammelgard.repository.GameRepository;
import se.torgammelgard.service.GameService;

/**
 * An implementation of the service.
 * 
 * @author torgammelgard
 *
 */
@Component
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAll() {
        ArrayList<Game> games = new ArrayList<>();
        Iterable<Game> itGames= gameRepository.findAll();
        for (Game g : itGames) {
            games.add(g);
        }
        return games;
    }

    @Override
    public Game save(Game game) {
        return gameRepository.saveAndFlush(game);
    }

    @Override
    public void remove(Game game) {
        gameRepository.delete(game);
    }
}
