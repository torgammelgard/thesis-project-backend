package se.torgammelgard.web;

import lombok.*;
import se.torgammelgard.persistence.entities.Match;

import java.util.List;

@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class MatchTable {

    @NonNull
    private List<Match> matches;
    private Long[] selectedMatches;
}
