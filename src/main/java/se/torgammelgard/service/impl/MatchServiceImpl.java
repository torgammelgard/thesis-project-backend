package se.torgammelgard.service.impl;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.torgammelgard.dto.MatchDto;
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
    public List<Match> findAllBelongingTo(Principal principal) throws UserNotFoundException {
    	User user = userRepository.findByUsername(principal.getName());
    	if (user == null)
    		throw new UserNotFoundException();
		return matchRepository.findAllBelongingTo(user);
    }
    
    @Override
    public Match find(Long id, Principal principal) throws UserNotFoundException {
    	User user = userRepository.findByUsername(principal.getName());
    	if (user == null)
    		throw new UserNotFoundException();
    	return matchRepository.findOneForUser(id, user);
    }

    @Override
    public Match saveAndFlush(Match match, Principal principal) throws UserNotFoundException {
        if (principal == null) {
            return null;
        }
        User user = userRepository.findByUsername(principal.getName());
        if (user == null) {
        	throw new UserNotFoundException();
        }
        
        //matchRepository.findOne(match.getId())
        match.setOwner(user);
        return matchRepository.saveAndFlush(match);
    }

    @Override
    public Match update(MatchDto matchDto, Principal principal) {
    	User user = userRepository.findByUsername(principal.getName());
		Match match = new Match();
		match.setId(matchDto.getId());
		match.setName(matchDto.getName());
		match.setFinished(matchDto.getFinished());
		match.setDate(matchDto.getDate());
		match.setTeamOne(matchDto.getTeamOne());
		match.setTeamTwo(matchDto.getTeamTwo());
		match.setTennisSets(matchDto.getTennisSets());
		match.setOwner(user);
    	return matchRepository.saveAndFlush(match);
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
