package se.torgammelgard.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.TeamRepository;
import se.torgammelgard.repository.UserRepository;
import se.torgammelgard.service.TeamService;

@Component
public class TeamServiceImpl implements TeamService {
	
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>(0);
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    @Transactional
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Transactional
    public Team save(Team team, Principal principal) {
        if (team == null)
            return null;
        User user = userRepository.findByUsername(principal.getName());
        team.setOwner(user);
        return save(team);
    }

    @Transactional
    public Team find(Long id) {
        return teamRepository.findOne(id);
    }
}
