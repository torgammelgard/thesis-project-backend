package se.torgammelgard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.repository.MatchRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>(0);
        matchRepository.findAll().forEach(matches::add);
        return matches;
    }

    public Match save(Match match) {
        return matchRepository.save(match);
    }

}
