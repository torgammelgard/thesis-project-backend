package se.torgammelgard.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.form.UserForm;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.service.UserService;

/**
 * A controller for the web page.
 * 
 * @author torgammelgard
 *
 */
@Controller
@RequestMapping("/account")
public class AccountPathController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping
	public String view(Model model, Principal principal) throws UserNotFoundException {
		// check user
		User user = checkUser(principal);
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		// send along a UserForm with the model to the view
		UserForm userForm = new UserForm();
		userForm.setUsername(user.getUsername());
		userForm.setFirstName(user.getFirstName());
		userForm.setLastName(user.getLastName());
		
		model.addAttribute("userForm", userForm);
		return "account";
	}
	
	@PostMapping(value = "/edit")
	public String edit(
			@Valid @ModelAttribute UserForm userForm, 
			BindingResult result,
			Principal principal,
			Model model) throws UserNotFoundException {
		if (result.hasErrors()) {
			 model.addAttribute("errors", result.getAllErrors());
            return "error_page";
		}
		User user = checkUser(principal);
		if (user == null) {
			throw new UserNotFoundException();
		}
		userForm.setId(user.getId());
		userForm.setUsername(user.getUsername());
		if (userForm.getPassword().equals("")) {
			userForm.setPassword(user.getPassword());	
		} else {
			userForm.setPassword(encoder.encode((userForm.getPassword())));
		}
		
		
		// Pass the result of the edit
		boolean edit_result;
		edit_result = userService.update(userForm) != null; 
		model.addAttribute("edit", edit_result);

		return "redirect:/account";
	}
	
	private User checkUser(Principal principal) {
		User user = null;
		if (principal != null) {
			user = userService.findByUsername(principal.getName()); 
		}
		return user;
	}
}
