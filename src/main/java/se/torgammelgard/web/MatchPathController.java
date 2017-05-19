package se.torgammelgard.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import se.torgammelgard.dto.MatchDto;
import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.service.MatchService;
import se.torgammelgard.service.TeamService;
import se.torgammelgard.service.UserService;

@Controller
@RequestMapping("/match")
public class MatchPathController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private UserService userService;

	@Autowired
	private MatchService matchService;

	public MatchPathController() {
	}

	@RequestMapping("/view")
	public String viewMatches(Model model, Principal principal) throws UserNotFoundException {
		List<Match> matches = new ArrayList<>(0);
		matches = matchService.findAllBelongingTo(principal);
		MatchTable matchTable = new MatchTable(matches);

		model.addAttribute("matchTable", matchTable);
		return "view_matches";
	}

	@RequestMapping(value = "/delete_page", method = RequestMethod.POST)
	public String delete(@ModelAttribute MatchTable matchTable) {
		for (Long id : matchTable.getSelectedMatches()) {
			matchService.delete(id);
		}
		return "redirect:/match/view";
	}

	@GetMapping(value = "/add")
	public String addMatch(Model model, Principal principal) throws UserNotFoundException {
		User owner = userService.findByUsername(principal.getName());
		if (owner == null) {
			throw new UserNotFoundException();
		}
		model.addAttribute("matchDto", new MatchDto());
		model.addAttribute("teams", teamService.findAllBelongingTo(principal));
		return "add_match";
	}

	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String matchmng(@Valid @ModelAttribute MatchDto matchDto, BindingResult bindingResult, Model model,
			HttpServletResponse response, Principal principal) throws UserNotFoundException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			return "error_page";
		}

		// Check if user is logged in (exists)
		User user = userService.findByUsername(principal.getName());
		if (principal == null || user == null) {
			throw new UserNotFoundException();
		}
		// TODO check in docs if matchDto can be null if no binding errors
		if (matchDto == null) {
		}

		//
		Match match = matchDto.convertToMatch();
		match.setOwner(user);

		matchService.saveAndFlush(match, principal);

		return "redirect:/match/view";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such User")
	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView userNotFound(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error");
		return mav;
	}
}
