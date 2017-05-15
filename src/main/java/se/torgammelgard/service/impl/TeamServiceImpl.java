package se.torgammelgard.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Team> findAllFor(Principal principal) throws UserNotFoundException {
    	List<Team> teams = new ArrayList<>();
    	User owner = userRepository.findByUsername(principal.getName());
    	if (owner == null) {
    		throw new UserNotFoundException();
    	}
    	for (Team team : teamRepository.findAll()) {
    		if (team.getOwner() == owner) {
    			teams.add(team);
    		}
    	}
    	return teams;
    }
    
    @Override
    public Team save(Team team, Principal principal) throws UserNotFoundException {
        if (team == null)
            return null;
        User user = userRepository.findByUsername(principal.getName());
        if (user == null) {
        	throw new UserNotFoundException();
        }
        team.setOwner(user);
        return teamRepository.save(team);
    }

    @Override
    public Team find(Long id) {
        return teamRepository.findOne(id);
    }
}
