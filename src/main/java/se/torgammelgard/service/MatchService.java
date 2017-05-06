package se.torgammelgard.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.persistence.entities.Match;

@Service
@Transactional
public interface MatchService {

    public List<Match> findAll();

    public Match save(Match match);

    public Match save(Match match, Principal principal);

    public void delete(long id);

    public void delete(Match match);
}
