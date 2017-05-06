package se.torgammelgard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.repository.TennisSetRepository;
import se.torgammelgard.service.TennisSetService;

@Component
public class TennisSetServiceImpl implements TennisSetService {

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
