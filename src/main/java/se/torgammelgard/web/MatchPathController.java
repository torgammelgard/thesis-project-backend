package se.torgammelgard.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import se.torgammelgard.dto.MatchDto;
import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.service.MatchService;
import se.torgammelgard.service.TeamService;
import se.torgammelgard.service.UserService;

@Controller
@RequestMapping("/match")
public class MatchPathController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

    public MatchPathController() {
    }

    @RequestMapping("/view_matches_page")
    public String viewMatches(Model model, Principal principal) throws UserNotFoundException {
        List<Match> matches = new ArrayList<>(0);
        matches = matchService.findAllFor(principal);
        MatchTable matchTable = new MatchTable(matches);

        model.addAttribute("matchTable", matchTable);
        return "view_matches";
    }

    @RequestMapping(value = "/delete_page", method = RequestMethod.POST)
    public String delete(@ModelAttribute MatchTable matchTable) {
        for (Long id : matchTable.getSelectedMatches()) {
            matchService.delete(id);
        }
        return "redirect:/match/view_matches_page";
    }

    private List<TennisSetScore> generateTennisSetScores() {
    	List<TennisSetScore> tennisSetScores = new ArrayList<TennisSetScore>();
    	for (int i = 0; i < 1; i++) {
    		TennisSetScore tennisSetScore = new TennisSetScore();
    		tennisSetScore.setScoreTeamOne(5);
    		tennisSetScores.add(tennisSetScore);
    	}
    	return tennisSetScores;
    }
    
    private List<TennisSet> generateTennisSets() {
    	List<TennisSet> tennisSets = new ArrayList<TennisSet>();
    	for (int i = 1; i <= 5; i++) {
    		TennisSet tennisSet = new TennisSet();
    		TennisSetScore tennisSetScore = new TennisSetScore();
    		tennisSetScore.setScoreTeamOne(4); //TODO
    		tennisSet.setTennisSetScore(tennisSetScore);
    		tennisSet.setSetNumber(i);
    		tennisSets.add(tennisSet);
    	}
		return tennisSets;
    }
    
    @RequestMapping("/add_form_page")
    public String addMatch(Model model, Principal principal) {
    	
//    	Match match = new Match();
//    	List<TennisSet> tennisSets = generateTennisSets();
//    	match.setTennisSets(tennisSets);
    	
    	//model.addAttribute("tennis_set", tennisSet);
        //model.addAttribute("setscore", new TennisSetScore());
    	User owner = userService.findByUsername(principal.getName());
    	if (owner != null) {
//	        model.addAttribute("match", match);
//	        model.addAttribute("tennis_sets", tennisSets);
    		model.addAttribute("matchDto", new MatchDto());
	        model.addAttribute("teams", teamService.findAllFor(principal)); // TODO handle what to do if < 2 teams
    	}
        return "add_match";
    }

    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String matchmng(
            @Valid @ModelAttribute MatchDto matchDto,
            BindingResult bindingResult,
            Model model,
            HttpServletResponse response,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "error_page";
        }

        // Check if user is logged in (exists)
        if (principal == null || userService.findByUsername(principal.getName()) == null) {
            throw new UserNotFoundException();
        }
        // TODO check in docs if matchDto can be null if no binding errors
        if (matchDto == null) {
        }

        // 
        Match match = matchDto.convertToMatch();
        
        matchService.save(match, principal);

        return "redirect:/api/match";
    }

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such User")
	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView userNotFound(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error");
		return mav;
	}
}
