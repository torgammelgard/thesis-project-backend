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
import org.springframework.web.bind.annotation.PathVariable;
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
    		Model model) {
    	
    	ModelAndView mav = new ModelAndView();
    	if (error != null) {
    		mav.addObject("error", "Login error");
    	}
    	
    	// add a loginDto for the login form
    	mav.addObject("loginDto", new LoginDto());
    	
    	// add a userDto for the registration form
    	if (!model.containsAttribute("userDto")) {
    		mav.addObject("userDto", new UserDto());
    	}
    	mav.setViewName("login");
    	
    	return mav;
    }
    
//    @PostMapping("/perform_login")
//    public ModelAndView performLogin(@Valid LoginDto logDto, BindingResult result, WebRequest request, Model model, Errors errors) {
//    	
//    	if (result.hasErrors()) {
//    		return new ModelAndView("/login", "errors", errors);
//    	}
//    	
//    	return new ModelAndView("index"); 
//    }
    
    @PostMapping(value = "/registration", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView registration(
    		@Valid @ModelAttribute UserDto userDto,
    		BindingResult result, 
    		WebRequest request, 
    		Model model, 
    		Errors errors) throws EmailExistsException {
    
    	User newUser = null;
        
    	if (!result.hasErrors()) {
    		try {
        		newUser = userService.registerNewUser(userDto);
        	} catch (EmailExistsException e) {
        		// TODO handle this exception
        	}		
    	}
    	
    	if (!result.hasErrors()) {
    		return new ModelAndView("successful_registration", "new_user", newUser);
    	} else {
    		model.addAttribute("loginDto", new LoginDto());
    		model.addAttribute("userDto", userDto);
    		return new ModelAndView("login", model.asMap());
    	}
    }
    
    @RequestMapping("/successfulregistration/{id}")
    public String successfulRegistration(@PathVariable String id,
    		Model model, 
    		HttpServletRequest request) {
    	model.addAttribute("new_user", userService.findById(Long.parseLong(id)));
    	return "successful_registration";
    }
}
