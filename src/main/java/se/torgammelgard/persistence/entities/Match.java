package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import se.torgammelgard.Views;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MATCHES")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long id;

    @NonNull
    @JsonView(Views.Public.class)
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "team_1_id", foreignKey = @ForeignKey(name = "TEAM_1_ID_FK"))
    @NonNull
    @Setter(AccessLevel.NONE)
    @JsonView(Views.Public.class)
    private Team teamOne;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "team_2_id", foreignKey = @ForeignKey(name = "TEAM_2_ID_FK"))
    @NonNull
    @Setter(AccessLevel.NONE)
    @JsonView(Views.Public.class)
    private Team teamTwo;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "match_tennisset",
            joinColumns = {@JoinColumn(name = "match_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tennisset_id", referencedColumnName = "id")})
    private List<TennisSet> tennisSets = new ArrayList<>(0);

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "owner_user_id", foreignKey = @ForeignKey(name = "OWNER_USER_ID_FK"))
    @JsonView(Views.Public.class)
    private User owner;

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
        teamOne.addTeam1_match(this);
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
        teamOne.addTeam2_match(this);
    }
}