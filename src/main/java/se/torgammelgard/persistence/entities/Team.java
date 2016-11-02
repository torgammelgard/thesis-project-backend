package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.web.bind.annotation.RequestAttribute;
import se.torgammelgard.Views;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data
@ToString(exclude = {"team1_matches", "team2_matches"})
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    @JsonView(Views.Public.class)
    private Long team_id;

    @NonNull
    @JsonView(Views.Public.class)
    private String teamName;

    @NonNull
    @JsonView(Views.Public.class)
    private String playerOneName;

    @NonNull
    @JsonView(Views.Public.class)
    private String playerTwoName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamOne", orphanRemoval = true)
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private List<Match> team1_matches = new ArrayList<>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamTwo", orphanRemoval = true)
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private List<Match> team2_matches = new ArrayList<>(0);

    public List<Match> getTeam1_matches() {
        return team1_matches;
    }

    public List<Match> getTeam2_matches() {
        return team2_matches;
    }


    public void addTeam1_match(Match match) {
        team1_matches.add(match);
    }

    public void addTeam2_match(Match match) {
        team2_matches.add(match);
    }

    public void removeTeam1_match(Match match) {
        team1_matches.remove(match);
        match.setTeamOne(null);
    }

    public void removeTeam2_match(Match match) {
        team2_matches.remove(match);
        match.setTeamTwo(null);
    }

}
