package se.torgammelgard.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.torgammelgard.Views;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @ModelAttribute("allMatches")
    public List<Match> populateMatches() {
        return matchService.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public
    @ResponseBody
    List<Match> getAllMatches() {
        return matchService.findAll();
    }

    @PostMapping
    public
    @ResponseBody
    Match addMatch(@RequestBody Match match) {
        return matchService.save(match);
    }
}
