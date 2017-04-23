package se.torgammelgard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.TeamRepository;
import se.torgammelgard.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>(0);
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team save(Team team, Principal principal) {
        if (team == null)
            return null;
        User user = userRepository.findByUsername(((User) principal).getUsername());
        team.setOwner(user);
        return save(team);
    }

    public Team find(Long id) {
        return teamRepository.findOne(id);
    }
}
