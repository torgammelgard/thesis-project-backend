package se.torgammelgard.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.repository.TennisSetScoreRepository;
import se.torgammelgard.service.TennisSetScoreService;

/**
 * An implementation of the service.
 * 
 * @author torgammelgard
 *
 */
@Component
public class TennisSetScoreServiceImpl implements TennisSetScoreService {

    @Autowired
    private TennisSetScoreRepository tennisSetScoreRepository;

    @Override
    public List<TennisSetScore> findAll() {
        List<TennisSetScore> tennisSetScores = Collections.emptyList();
        tennisSetScoreRepository.findAll().forEach(tennisSetScores::add);
        return tennisSetScores;
    }

    @Override
    public TennisSetScore save(TennisSetScore tennisSetScore) {
        return tennisSetScoreRepository.save(tennisSetScore);
    }
}
