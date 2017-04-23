package se.torgammelgard.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import se.torgammelgard.Views;

@Entity
@Table(name = "TEAMS")
@Data
@ToString(exclude = {"team1_matches", "team2_matches"})
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    @JsonView(Views.Public.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", foreignKey = @ForeignKey(name = "OWNER_USER_ID_FK"))
    @JsonView(Views.Public.class)
    private User owner;

    @NonNull
    @JsonView(Views.Public.class)
    private String teamName;

    @NonNull
    @JsonView(Views.Public.class)
    private String playerOneName;

    @NonNull
    @JsonView(Views.Public.class)
    private String playerTwoName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamOne", orphanRemoval = false)
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private List<Match> team1_matches = new ArrayList<>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamTwo", orphanRemoval = false)
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
