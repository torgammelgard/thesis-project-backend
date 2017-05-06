package se.torgammelgard.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>(0);
        matchRepository.findAll().forEach(matches::add);
        return matches;
    }

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public Match save(Match match, Principal principal) {
        if (principal == null)
            return null;
        User user = userRepository.findByUsername(principal.getName());
        match.setOwner(user);
        return save(match);
    }

    public void delete(long id) {
        matchRepository.delete(id);
    }

    public void delete(Match match) {
        matchRepository.delete(match);
    }
}
