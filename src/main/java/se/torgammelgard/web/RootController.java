package se.torgammelgard.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class RootController {

    private DateFormat dateFormat = DateFormat.getDateInstance();

    @RequestMapping
    public String root(Model model) {
        model.addAttribute("serverTime", dateFormat.format(new Date()));
        return "index";
    }
}
