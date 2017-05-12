package se.torgammelgard.web;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import se.torgammelgard.exception.EmailExistsException;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.service.UserService;

@Controller
@RequestMapping("/")
public class RootController {

    private DateFormat dateFormat = DateFormat.getDateInstance();

    @Autowired
    private UserService userService;
    
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
    
    @PostMapping("/registration")
    public ModelAndView registration(@Valid UserDto userDto, BindingResult result, WebRequest request, Errors errors) 
    		throws EmailExistsException {
    	
    	User newUser = null;
    	try {
    		newUser = userService.registerNewUser(userDto);
    	} catch (EmailExistsException e) {
    	}
    	
    	if (newUser != null) {
    		return new ModelAndView("successful_registration", "new_user", newUser);
    	} else {
    		return new ModelAndView("login");
    	}
    }
    
    @RequestMapping("/successfulregistration")
    public String successfulRegistration(Model model, HttpServletRequest request) {
    	//User newUser = (User) request.getAttribute("new_user");
    	//model.addAttribute("new_user", newUser);
    	return "successful_registration";
    }
}
