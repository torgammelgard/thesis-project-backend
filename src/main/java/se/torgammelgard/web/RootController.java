package se.torgammelgard.web;

import java.text.DateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import se.torgammelgard.dto.UserDto;

@Controller
@RequestMapping("/")
public class RootController {

    private DateFormat dateFormat = DateFormat.getDateInstance();

    @RequestMapping
    public String root(Model model) {
        model.addAttribute("serverTime", dateFormat.format(new Date()));
        return "index";
    }

    @RequestMapping("/login")
    public ModelAndView login(
    		@RequestParam(value = "error", required = false) String error,
    		@RequestParam(value = "logout", required = false) String logout) {
    	
    	ModelAndView mav = new ModelAndView();
    	if (error != null) {
    		mav.addObject("error", "Login error");
    	}
    	if (logout != null) {
    		mav.addObject("logout", "Logout successful");
    	}
    	
    	// add a user for the registration form
    	UserDto userDto = new UserDto();
    	mav.addObject("user", userDto);
    	
    	mav.setViewName("login");
    	
    	return mav;
    }
    
    @PostMapping("/register")
    public ModelAndView register(@Valid UserDto userDto, BindingResult result, WebRequest request, Errors errors) {
    	ModelAndView mav = new ModelAndView();
    	
    	mav.addObject("msg", "Welcome new member");
    	mav.setViewName("redirect:index");
    	return mav;
    }
}
