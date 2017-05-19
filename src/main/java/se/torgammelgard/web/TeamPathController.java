package se.torgammelgard.web;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import se.torgammelgard.dto.TeamDto;
import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamPathController {

	@Autowired
	private TeamService teamService;
	
	@ModelAttribute("teamDto")
	public TeamDto populateTeamDto() {
		return new TeamDto();
	}
	
	@GetMapping(value = "/add")
	public String createTeamPage() {
		return "add_team";
	}
	
	@GetMapping(value = "/view")
	public String view(Model model, Principal principal) throws UserNotFoundException {
		TeamTable teamTable = new TeamTable();
		teamTable.setTeams(teamService.findAllFor(principal));
		model.addAttribute("teamTable", teamTable);
		return "/team_view";
	}
	
	@PostMapping(value = "/save")
	public String saveTeam(
			@Valid @ModelAttribute TeamDto teamDto,
			BindingResult bindingResult,
            Model model,
            HttpServletResponse response,
            Principal principal) throws UserNotFoundException {
		
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "error_page";
        } 

        Team team = new Team();
        team.setPlayerOneName(teamDto.getPlayerOneName());
        team.setPlayerTwoName(teamDto.getPlayerTwoName());
        teamService.save(team, principal);
        
        return "redirect:/team/view";
	}
	
	@PostMapping(value = "/delete")
	public String delete(
			@ModelAttribute TeamTable teamTable, 
			BindingResult result) {
		for (Long id : teamTable.getSelectedTeams()) {
			teamService.delete(id);
		}
		return "redirect:/team/view";
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
