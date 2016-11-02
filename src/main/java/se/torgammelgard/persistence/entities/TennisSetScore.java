package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import se.torgammelgard.Views;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "set_scores")
@Data
public class TennisSetScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long setscore_id;

    @JsonView(Views.Public.class)
    private int setScoreTeamOne;

    @JsonView(Views.Public.class)
    private int setScoreTeamTwo;

    @ManyToMany(mappedBy = "tennisSetScore", cascade = CascadeType.ALL)
    private Set<TennisSet> tennisSets = new HashSet<>(0);
}
