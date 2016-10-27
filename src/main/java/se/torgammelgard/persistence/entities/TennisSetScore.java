package se.torgammelgard.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "set_scores")
@Data
public class TennisSetScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long setscore_id;

    private int setScoreTeamOne;

    private int setScoreTeamTwo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "setscore_tennisset", joinColumns = {@JoinColumn(name = "setscore_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tennisset_id", nullable = false)})
    private Set<TennisSet> tennisSets = new HashSet<>(0);
}
