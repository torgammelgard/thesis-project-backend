package se.torgammelgard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.repository.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Iterable<Team> findAll(){
        return teamRepository.findAll();
    }

    public Team save(Team team) {return teamRepository.save(team);}
}
