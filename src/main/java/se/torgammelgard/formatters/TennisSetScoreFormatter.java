package se.torgammelgard.formatters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.service.TennisSetScoreService;

import java.text.ParseException;
import java.util.Locale;

public class TennisSetScoreFormatter implements Formatter<TennisSetScore> {

    @Autowired
    private TennisSetScoreService tennisSetScoreService;

    @Override
    public TennisSetScore parse(String text, Locale locale) throws ParseException {
        System.out.println(text);
//        tennisSetScoreService.find()
        return new TennisSetScore();
    }

    @Override
    public String print(TennisSetScore object, Locale locale) {
        return null;
    }
}
