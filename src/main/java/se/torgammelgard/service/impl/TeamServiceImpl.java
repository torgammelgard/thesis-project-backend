package se.torgammelgard.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Team> findAllTeamsWithMatches(Principal principal) throws UserNotFoundException {
    	User user = findUser(principal);
    	return teamRepository.findAllTeamsWithMatches(user);
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
    	List<Team> teams = teamRepository.findAllTeamsWithMatches(user);
    	if (teams.isEmpty()) {
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
}
