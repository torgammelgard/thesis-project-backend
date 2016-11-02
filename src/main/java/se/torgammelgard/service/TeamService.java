package se.torgammelgard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>(0);
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team find(Long id) {
        return teamRepository.findOne(id);
    }
}
