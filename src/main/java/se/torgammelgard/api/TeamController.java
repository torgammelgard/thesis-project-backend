package se.torgammelgard.api;

import java.security.Principal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;
import se.torgammelgard.persistence.entities.JunkEntity;
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    //@JsonView(Views.Public.class)
    public @ResponseBody String addTeam(@RequestBody Team team) {
    	//team.setOwner(userRepo.findByUsername("tor"));
    	//request.getSession().setAttribute("team", new Team());
    	//Team persistent_team = teamService.save(team);
    	return "OK";
    }
    
    
    @PostMapping(value = "/junk", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    //@JsonView(Views.Public.class)
    public @ResponseBody JunkEntity addTeam(@RequestBody JunkEntity junk) {
    	return junk;
    }
    
    
    // TODO should be deleted later and replaced with POST
    @RequestMapping("/add")
    public void addRandomTeam(Principal principal) {
        Team team = new Team();
        team.setTeamName(String.format("Team name with random number %d", new Random().nextInt(100)));
        User user_1 = userRepo.getOne(0L);
        team.setOwner(user_1);
        teamService.save(team, principal);
    }
    
	@ExceptionHandler
	public @ResponseBody String exHandle(Exception e) {
		return e.getCause().getMessage();
	}
	
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleErrors(HttpServletRequest req, Exception ex) {
//    	ModelAndView mav = new ModelAndView();
//    	mav.addObject("exception", ex);
//    	mav.addObject("url", req.getRequestURL());
//    	mav.setViewName("error");
//    	return mav;
//    }
}
