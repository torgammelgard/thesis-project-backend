package se.torgammelgard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.persistence.entities.TennisSet;

/**
 * A service for handling tennis sets.
 * 
 * @author torgammelgard
 *
 */
@Service
@Transactional
public interface TennisSetService {

    public List<TennisSet> findAll();

    public TennisSet save(TennisSet tennisSet);
}
