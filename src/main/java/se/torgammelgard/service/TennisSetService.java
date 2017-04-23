package se.torgammelgard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.repository.TennisSetRepository;

@Service
@Transactional
public class TennisSetService {

    @Autowired
    private TennisSetRepository tennisSetRepository;

    public List<TennisSet> findAll() {
        List<TennisSet> tennisSets = new ArrayList<>(0);
        tennisSetRepository.findAll().forEach(tennisSets::add);
        return tennisSets;
    }

    public TennisSet save(TennisSet tennisSet) {
        return tennisSetRepository.save(tennisSet);
    }
}
