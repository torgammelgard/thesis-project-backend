package se.torgammelgard.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;
import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.service.MatchService;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

//    @ModelAttribute("allMatches")
//    public List<Match> populateMatches(Principal principal) {
//        return matchService.findAll();
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public @ResponseBody List<Match> getAllMatches(Principal principal) throws UserNotFoundException {
        return matchService.findAllFor(principal);
    }

    @PostMapping
    @JsonView(Views.Public.class)
    public @ResponseBody Match addMatch(@RequestBody Match match, Principal principal) {
        return matchService.save(match, principal);
    }
}
