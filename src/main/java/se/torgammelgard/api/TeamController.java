package se.torgammelgard.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;
import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.service.TeamService;

/**
 * API - A rest controller for handling teams.
 */
@Controller
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    /*
     * Get mapping for retrieving a list of teams, belonging to the logged in user.
     * @param a principal (authenticated user)
     * @return a list of teams belonging to the authenticated user
     */
    @GetMapping
    @JsonView(Views.Public.class)
    public @ResponseBody List<Team> getAllTeams(Principal principal) throws UserNotFoundException {
        return teamService.findAllBelongingTo(principal);
    }


    /*
     * Post mapping for storing a team. 
     * @param a Team
     * @param a principal (authenticated user)
     * @return the persisted team
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @JsonView(Views.Public.class)
    public @ResponseBody Team addTeam(@RequestBody Team team, Principal principal) throws UserNotFoundException{
    	return teamService.saveAndFlush(team, principal);
    }
    
    /*
     * An exception handler. Handles all exceptions in this controller.
     * @return a message of what caused the error
     */
	@ExceptionHandler
	public @ResponseBody String exHandle(Exception e) {
		return e.getCause().getMessage();
	}
}
