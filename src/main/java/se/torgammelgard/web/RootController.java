package se.torgammelgard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.service.MatchService;
import se.torgammelgard.service.TeamService;

import java.text.DateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class RootController {

    private DateFormat dateFormat = DateFormat.getDateInstance();

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @RequestMapping
    public String root(Model model) {
        model.addAttribute("serverTime", dateFormat.format(new Date()));
        return "index";
    }

    @RequestMapping("/addmatch")
    public String addMatch(Model model) {
        model.addAttribute("match", new Match());
        model.addAttribute("teams", teamService.findAll());
        return "addmatch";
    }

    @RequestMapping(value = "/savematch", method = RequestMethod.POST)
    public String matchmng(@ModelAttribute Match match, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "index";
        }
        Match savedMatch = matchService.save(match);
        return "redirect:/api/match";
    }
}
