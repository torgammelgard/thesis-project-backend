package se.torgammelgard.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import se.torgammelgard.persistence.entities.TennisSetScore;

public class TennisSetScoreFormatter implements Formatter<TennisSetScore> {

    //@Autowired
    //private TennisSetScoreService tennisSetScoreService;

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
