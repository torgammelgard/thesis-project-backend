package se.torgammelgard.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.dto.MatchDto;
import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Match;

@Service
@Transactional
public interface MatchService {

    public List<Match> findAllBelongingTo(Principal principal) throws UserNotFoundException;

    public Match find(Long id, Principal principal) throws UserNotFoundException;
    
    public Match saveAndFlush(Match match, Principal principal);

    public Match update(MatchDto matchDto, Principal principal);
    
    public void delete(long id);

    public void delete(Match match);
}
