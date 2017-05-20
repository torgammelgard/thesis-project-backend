package se.torgammelgard.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.dto.TeamDto;
import se.torgammelgard.exception.TeamOwnsMatchesException;
import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.TeamRepository;
import se.torgammelgard.repository.UserRepository;
import se.torgammelgard.service.TeamService;

@Component
@Transactional
public class TeamServiceImpl implements TeamService {
	
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Team> findAllBelongingTo(Principal principal) throws UserNotFoundException {
    	User user = findUser(principal);
    	return teamRepository.findAllTeamsBelongingTo(user);
    }
    
	@Override
	public Team find(Long id, Principal principal) throws UserNotFoundException {
		User user = findUser(principal);
		return teamRepository.findOneForUser(id, user);
	}
	
    @Override
    public Team saveAndFlush(Team team, Principal principal) throws UserNotFoundException {
        if (team == null)
            return null;
        User user = findUser(principal);
        team.setOwner(user);
        return teamRepository.saveAndFlush(team);
    }

    @Override
    public void delete(Long id, Principal principal) throws UserNotFoundException, TeamOwnsMatchesException {
    	User user = findUser(principal);
    	Team team = teamRepository.findOne(id);
    	if (team != null && team.getOwner() == user && (team.getTeam1_matches().isEmpty() && team.getTeam2_matches().isEmpty())) {
    		teamRepository.delete(id);
    	} else {
    		throw new TeamOwnsMatchesException();
    	}
    }
    
    @Override
    public Team find(Long id) {
        return teamRepository.findOne(id);
    }
    
    private User findUser(Principal principal) throws UserNotFoundException {
    	User user = userRepository.findByUsername(principal.getName());
    	if (user == null) {
    		throw new UserNotFoundException();
    	}
    	return user;
    }

	@Override
	public Team update(TeamDto teamDto, Principal principal) throws UserNotFoundException {
    	User user = findUser(principal);
    	Team team = new Team();
    	team.setId(teamDto.getId());
    	team.setTeamName(teamDto.getTeamName());
    	team.setOwner(user);
    	team.setPlayerOneName(teamDto.getPlayerOneName());
    	team.setPlayerTwoName(teamDto.getPlayerTwoName());
    	return teamRepository.saveAndFlush(team);
	}
}
