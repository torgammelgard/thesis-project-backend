package se.torgammelgard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.User;
import se.torgammelgard.repository.MatchRepository;
import se.torgammelgard.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MatchService {

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
        User user = userRepository.findByUsername(principal.getName());
        match.setOwner(user);
        return save(match);
    }

    public void delete(long id) {
        matchRepository.delete(id);
    }
}
