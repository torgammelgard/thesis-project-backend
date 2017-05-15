package se.torgammelgard.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.service.TennisSetScoreService;

public class TennisSetScoreFormatter implements Formatter<TennisSetScore> {

    @Autowired
    private TennisSetScoreService tennisSetScoreService;

    @Override
    public TennisSetScore parse(final String scoresString, Locale locale) throws ParseException {
        tennisSetScoreService.findAll();
        return new TennisSetScore();
    }

    @Override
    public String print(TennisSetScore tennisSetScore, Locale locale) {
        return null;
    }
}
