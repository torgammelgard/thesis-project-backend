package se.torgammelgard.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import se.torgammelgard.persistence.entities.Match;

/**
 * A helper class for storing information about which matches in the table on the web page have been selected
 * 
 * @author torgammelgard
 *
 */
@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class MatchTable {

    @NonNull
    private List<Match> matches;
    private Long[] selectedMatches;
}
