package se.torgammelgard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;
import se.torgammelgard.persistence.entities.Game;
import se.torgammelgard.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAll() {
        ArrayList<Game> games = new ArrayList<>();
        Iterable<Game> itGames= gameRepository.findAll();
        for (Game g : itGames) {
            games.add(g);
        }
        return games;
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void removeGame(Game game) {
        gameRepository.delete(game);
    }
}
