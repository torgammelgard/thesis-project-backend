package se.torgammelgard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.repository.TennisSetRepository;
import se.torgammelgard.service.TennisSetService;

/**
 * An implementation of the service.
 * 
 * @author torgammelgard
 *
 */
@Component
public class TennisSetServiceImpl implements TennisSetService {

    @Autowired
    private TennisSetRepository tennisSetRepository;

    @Override
    public List<TennisSet> findAll() {
        List<TennisSet> tennisSets = new ArrayList<>(0);
        tennisSetRepository.findAll().forEach(tennisSets::add);
        return tennisSets;
    }

    @Override
    public TennisSet save(TennisSet tennisSet) {
    	TennisSetScore tennisSetScore = tennisSet.getTennisSetScore();
    	boolean proceedWithSave = true;
    	// TODO fix if this should be here
//		if (verify(tennisSetScore)) {
//			proceedWithSave = false;
//		}
    	if (proceedWithSave) {
    		return tennisSetRepository.save(tennisSet);
    	} else {
    		return null;
    	}
    }
    
    private boolean verify(TennisSetScore tennisSetScore) {
    	int s1 = tennisSetScore.getScoreTeamOne();
    	int s2 = tennisSetScore.getScoreTeamTwo();
    	
    	if (Math.abs(s1 - s2) >= 2 && s1 == 6 || s2 == 6)
    		return true;
    	
    	if ((s1 == 6 && s2 == 7) || (s1 == 7 && s2 == 6))
    		return true;
    	
    	return false;
    }
}
