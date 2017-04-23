package se.torgammelgard.api;

import java.security.Principal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.UserRepository;
import se.torgammelgard.service.TeamService;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserRepository userRepo;
    
    @GetMapping
    @JsonView(Views.Public.class)
    public @ResponseBody List<Team> getAllTeams() {
        return teamService.findAll();
    }

    // TODO should be deleted later and replaced with POST
    @RequestMapping("/add")
    public void addTeam(Principal principal) {
        Team team = new Team();
        team.setTeamName(String.format("Team name with random number %d", new Random().nextInt(100)));
        User user_1 = userRepo.getOne(0L);
        team.setOwner(user_1);
        teamService.save(team, principal);
    }
}
