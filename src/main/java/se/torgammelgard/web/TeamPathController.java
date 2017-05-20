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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import se.torgammelgard.dto.TeamDto;
import se.torgammelgard.exception.TeamOwnsMatchesException;
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
	public String view(@RequestParam(value = "matches_team_owns", required = false) Long matchesTeamOwns, Model model, Principal principal) throws UserNotFoundException {
		TeamTable teamTable = new TeamTable();
		teamTable.setTeams(teamService.findAllBelongingTo(principal));
		model.addAttribute("teamTable", teamTable);
		if (matchesTeamOwns != null) {
			model.addAttribute("matches_team_owns", matchesTeamOwns);
		}
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
        team.setTeamName(teamDto.getTeamName());
        team.setPlayerOneName(teamDto.getPlayerOneName());
        team.setPlayerTwoName(teamDto.getPlayerTwoName());
        teamService.saveAndFlush(team, principal);
        
        return "redirect:/team/view";
	}
	
	@GetMapping("/edit/{id}")
	public String editMatch(@PathVariable Long id, Model model, Principal principal) {
		// populate model with match (id)
		Team team = teamService.find(id, principal);
		TeamDto teamDto = TeamDto.build(team);
		model.addAttribute("teamDto", teamDto);
		return "team_edit";
	}
	
	@PostMapping("/edit")
	public String doEditMatch(
			@ModelAttribute TeamDto teamDto, 
			BindingResult result,
			Model model,
			Principal principal) throws UserNotFoundException  {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "error_page";
		}
		
		teamService.update(teamDto, principal);
		return "redirect:/team/view";
	}
	
	@PostMapping(value = "/delete")
	public String delete(
			@ModelAttribute TeamTable teamTable, 
			BindingResult result,
			Principal principal,
			Model model) throws UserNotFoundException {
		for (Long id : teamTable.getSelectedTeams()) {
			try {
				teamService.delete(id, principal);
			} catch (TeamOwnsMatchesException e) {
				Team team = teamService.find(id);
				int total_matches = team.getTeam1_matches().size() + team.getTeam2_matches().size();
				model.addAttribute("matches_team_owns", total_matches);
			}
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
