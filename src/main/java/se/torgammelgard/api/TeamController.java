package se.torgammelgard.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.torgammelgard.Views;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.service.TeamService;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping
    @JsonView(Views.Public.class)
    public @ResponseBody List<Team> getAllTeams() {
        return teamService.findAll();
    }

    @RequestMapping("/add")
    public void addTeam() {
        Team team = new Team();
        team.setTeamName(String.format("Team name with random number %d", new Random().nextInt(100)));
        teamService.save(team);
    }
}
