package se.torgammelgard.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<String> getGame() {
//        Game game = new Game();
//        game.setName("Testing");
        return new HttpEntity<>("Testing");
    }
}
