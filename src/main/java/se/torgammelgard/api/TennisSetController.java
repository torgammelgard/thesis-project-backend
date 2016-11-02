package se.torgammelgard.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.torgammelgard.Views;
import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.service.TennisSetService;

import java.util.List;

@Controller
@RequestMapping("/api/tennisset")
public class TennisSetController {

    @Autowired
    private TennisSetService tennisSetService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public @ResponseBody
    List<TennisSet> listall() {
        return tennisSetService.findAll();
    }
}
