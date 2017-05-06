package se.torgammelgard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.persistence.entities.TennisSetScore;

@Service
@Transactional
public interface TennisSetScoreService {

    public List<TennisSetScore> findAll();

    public TennisSetScore save(TennisSetScore tennisSetScore);
}
