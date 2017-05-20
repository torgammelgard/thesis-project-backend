package se.torgammelgard.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.dto.TeamDto;
import se.torgammelgard.exception.TeamOwnsMatchesException;
import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Team;

@Service
@Transactional
public interface TeamService {

    public List<Team> findAllBelongingTo(Principal principal) throws UserNotFoundException;
    
    public Team find(Long id, Principal principal) throws UserNotFoundException;
    
    public Team update(TeamDto teamDto, Principal principal) throws UserNotFoundException;
    
    public Team saveAndFlush(Team team, Principal principal) throws UserNotFoundException;

    public void delete(Long id, Principal principal) throws UserNotFoundException, TeamOwnsMatchesException;
    
    public Team find(Long id);
}
