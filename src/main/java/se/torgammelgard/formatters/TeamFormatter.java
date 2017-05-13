package se.torgammelgard.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.service.TeamService;

@Component
public class TeamFormatter implements Formatter<Team> {

    @Autowired
    private TeamService teamService;

    @Override
    public Team parse(final String id, Locale locale) throws ParseException {
        return teamService.find(Long.valueOf(id));
    }

    @Override
    public String print(Team team, Locale locale) {
        return Long.toString(team.getId());
    }
}
