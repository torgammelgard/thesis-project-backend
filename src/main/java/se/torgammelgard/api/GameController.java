package se.torgammelgard.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.torgammelgard.Views;
import se.torgammelgard.persistence.entities.Game;
import se.torgammelgard.service.GameService;

import java.util.List;

@RestController
@RequestMapping(value = "api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public @ResponseBody List<Game> getGames() {
        List<Game> games = gameService.findAll();
        return games;
    }
    
}
