package se.torgammelgard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.service.MatchService;
import se.torgammelgard.service.TeamService;

@Controller
@RequestMapping("/match")
public class MatchPathController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @RequestMapping("/view_matches_page")
    public String viewMatches(Model model) {
        model.addAttribute("matches", matchService.findAll());
        return "view_matches";
    }

    @RequestMapping("/add_form_page")
    public String addMatch(Model model) {
        model.addAttribute("match", new Match());
        model.addAttribute("teams", teamService.findAll());
        return "add_match";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String matchmng(@ModelAttribute Match match, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "error_page";
        }
        matchService.save(match);
        return "redirect:/api/match";
    }
}
