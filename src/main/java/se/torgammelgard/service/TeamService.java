package se.torgammelgard.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.exception.UserNotFoundException;
import se.torgammelgard.persistence.entities.Team;

@Service
@Transactional
public interface TeamService {

    public List<Team> findAllFor(Principal principal) throws UserNotFoundException;

    public Team save(Team team, Principal principal) throws UserNotFoundException;

    public void delete(Long id);
    
    public Team find(Long id);
}
