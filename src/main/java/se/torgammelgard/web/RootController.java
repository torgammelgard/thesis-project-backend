package se.torgammelgard.web;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import se.torgammelgard.dto.LoginDto;
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
    
    @ModelAttribute(name = "serverTime")
    public String serverTime() {
    	return dateFormat.format(new Date());
    }
    
    @RequestMapping
    public String root(Model model) {
        return "index";
    }

    @GetMapping("/successful_logout")
    public String logout() {
    	return "logged_out";
    }
    
    @GetMapping("/login")
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
    	
    	// add a loginDto for the login form
    	mav.addObject("loginDto", new LoginDto());
    	
    	// add a userDto for the registration form
    	mav.addObject("userDto", new UserDto());
    	
    	mav.setViewName("login");
    	
    	return mav;
    }
    
    @PostMapping("/perform_login")
    public ModelAndView performLogin(@Valid LoginDto logDto, BindingResult result, WebRequest request, Model model, Errors errors) {
    	
    	if (result.hasErrors()) {
    		return new ModelAndView("/login", "errors", errors);
    	}
    	
    	return new ModelAndView("index"); 
    }
    
    @PostMapping(value = "/registration", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView registration(@Valid @ModelAttribute UserDto userDto, BindingResult result, WebRequest request, Model model, Errors errors) 
    		throws EmailExistsException {
    	
    	if (result.hasErrors()) {
    		return new ModelAndView("login", "errors", errors);
    	}
    	
    	User newUser = null;
    	try {
    		newUser = userService.registerNewUser(userDto);
    	} catch (EmailExistsException e) {
    	}
    	
    	if (newUser != null) {
    		model.asMap().clear(); // TODO test if this is needed or not
    		return new ModelAndView("successful_registration", "new_user", newUser);
    	} else {
    		return new ModelAndView("login");
    	}
    }
    
    @RequestMapping("/successfulregistration")
    public String successfulRegistration(Model model, HttpServletRequest request) {
    	return "successful_registration";
    }
}
