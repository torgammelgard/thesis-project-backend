package se.torgammelgard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.repository.TennisSetScoreRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class TennisSetScoreService {

    @Autowired
    private TennisSetScoreRepository tennisSetScoreRepository;

    public List<TennisSetScore> findAll() {
        List<TennisSetScore> tennisSetScores = Collections.emptyList();
        tennisSetScoreRepository.findAll().forEach(tennisSetScores::add);
        return tennisSetScores;
    }

    public TennisSetScore save(TennisSetScore tennisSetScore) {
        return tennisSetScoreRepository.save(tennisSetScore);
    }
}
