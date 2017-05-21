package se.torgammelgard.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;
import se.torgammelgard.persistence.entities.Game;
import se.torgammelgard.repository.GameRepository;
import se.torgammelgard.service.GameService;

/*
 * A dummy controller for the dummy entity Game. Used for testing during production.
 */
@RestController
@RequestMapping("api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepo;
    
    /*
     *  Mapping for getting a list of games sorted on their id in ascending order.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public @ResponseBody List<Game> getGames() {
    	List<Game> games = gameRepo.findAll(new Sort(Sort.Direction.ASC, new String[]{"id"}));
        return games;
    }
    
    /*
     * Mapping for getting a list of games of a certain version using a path variable
     * @param a Long (version number)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{version}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public @ResponseBody List<Game> getSomeGames(@PathVariable("version") Long version ) {
    	List<Game> games = gameRepo.findByVersion(version);
        //List<Game> games = gameService.findAll();
        return games;
    }
    
    /*
     * Post mapping for saving a game.
     * @param a Game to be saved
     */
    @PostMapping
    @JsonView(Views.Public.class)
    public @ResponseBody Game saveGame(@RequestBody Game game) {
    	return gameService.save(game);
    }
}
