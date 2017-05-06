package se.torgammelgard.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.persistence.entities.Team;

@Service
public interface TeamService {

    @Transactional
    public List<Team> findAll();

    @Transactional
    public Team save(Team team);

    @Transactional
    public Team save(Team team, Principal principal);

    @Transactional
    public Team find(Long id);
}
