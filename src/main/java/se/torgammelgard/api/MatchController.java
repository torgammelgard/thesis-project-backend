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

/**
 * API - A rest controller for handling matches.
 */
@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    /*
     * Get mapping for retrieving a list of matches, belonging to the logged in user.
     * @param a principal (authenticated user)
     * @return a list of all matches belonging to the authenticated user
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public @ResponseBody List<Match> getAllMatches(Principal principal) throws UserNotFoundException {
        return matchService.findAllBelongingTo(principal);
    }


    /*
     * Post mapping for storing a match.
     * @param a Match
     * @param a principal (authenticated user)
     * @return the persisted match
     */
    @PostMapping
    @JsonView(Views.Public.class)
    public @ResponseBody Match addMatch(@RequestBody Match match, Principal principal) {
        return matchService.saveAndFlush(match, principal);
    }
}
