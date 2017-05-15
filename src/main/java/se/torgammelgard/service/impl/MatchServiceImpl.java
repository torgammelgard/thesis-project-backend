package se.torgammelgard.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.MatchRepository;
import se.torgammelgard.repository.UserRepository;
import se.torgammelgard.service.MatchService;

@Component
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Match> findAllFor(Principal principal) throws UserNotFoundException {
    	// check if user exists
    	User owner = userRepository.findByUsername(principal.getName());
    	if (owner == null) {
    		throw new UserNotFoundException();
    	}
    	
    	// find all matches belonging to the user
    	List<Match> matches = new ArrayList<>(0);
        matchRepository.findAll().forEach((match) -> {
        	if (match.getOwner().getId() == owner.getId()) {
        		matches.add(match);
        	}
        });
        
        return matches;
    }

    @Override
    public Match save(Match match, Principal principal) {
        if (principal == null) {
            return null;
        }
        User user = userRepository.findByUsername(principal.getName());
        if (user == null) {
        	return null;
        }
        match.setOwner(user);
        return matchRepository.save(match);
    }

    @Override
    public void delete(long id) {
        matchRepository.delete(id);
    }

    @Override
    public void delete(Match match) {
        matchRepository.delete(match);
    }
}
