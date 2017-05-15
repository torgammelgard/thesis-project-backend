package se.torgammelgard.api;

import java.security.Principal;
import java.util.List;
import java.util.Random;

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
import se.torgammelgard.service.UserService;

@Controller
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;
    
    @GetMapping
    @JsonView(Views.Public.class)
    public @ResponseBody List<Team> getAllTeams(Principal principal) {
        return teamService.findAllFor(principal);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @JsonView(Views.Public.class)
    public @ResponseBody Team addTeam(@RequestBody Team team, Principal principal) throws UserNotFoundException{
    	return teamService.save(team, principal);
    }
    
    // TODO should be deleted later and replaced with POST
    @RequestMapping("/add")
    @JsonView(Views.Public.class)
    public @ResponseBody Team addRandomTeam(Principal principal) {
        Team team = new Team();
        team.setTeamName(String.format("Team name with random number %d", new Random().nextInt(100)));
        return teamService.save(team, principal);
    }
    
	@ExceptionHandler
	public @ResponseBody String exHandle(Exception e) {
		return e.getCause().getMessage();
	}
}
