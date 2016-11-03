package se.torgammelgard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.UserRepository;
import se.torgammelgard.service.MatchService;
import se.torgammelgard.service.TeamService;
import se.torgammelgard.service.TennisSetScoreService;
import se.torgammelgard.service.TennisSetService;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchPathController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TennisSetScoreService tennisSetScoreService;

    @Autowired
    private TennisSetService tennisSetService;

    @Autowired
    private MatchService matchService;

    @RequestMapping("/view_matches_page")
    public String viewMatches(Model model) {
        model.addAttribute("matches", matchService.findAll());
        return "view_matches";
    }

    @RequestMapping("/add_form_page")
    public String addMatch(Model model) {
        model.addAttribute("setscore", new TennisSetScore());
        model.addAttribute("match", new Match());
        model.addAttribute("teams", teamService.findAll());
        return "add_match";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        matchService.delete(id);
        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String matchmng(
            @ModelAttribute Match match,
            @ModelAttribute TennisSetScore setscore,
            BindingResult bindingResult,
            Model model,
            HttpServletResponse response,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "error_page";
        }

        // Check if user is logged in (exists)
        if (principal == null || userRepository.findByUsername(principal.getName()) == null) {
            throw new UserNotFoundException();
        }

        List<TennisSetScore> tennisSetScores = new ArrayList<>(0);
        tennisSetScores.add(setscore);
        TennisSet tennisSet = new TennisSet();
        tennisSet.setTennisSetScore(tennisSetScores);
        List<TennisSet> tennisSets = new ArrayList<>(0);
        tennisSets.add(tennisSet);
        match.setTennisSets(tennisSets);
        matchService.save(match, principal);

        return "redirect:/api/match";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such User")
    public class UserNotFoundException extends RuntimeException {
    }
}
