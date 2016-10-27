package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import se.torgammelgard.Views;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tennis_sets")
@Data
public class TennisSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long tennisset_id;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tennisSets")
    private List<TennisSetScore> tennisSetScore = new ArrayList<>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "set_match", joinColumns = {@JoinColumn(name = "tennisset_id", nullable = false)},
    inverseJoinColumns = {@JoinColumn(name = "match_id", nullable = false)})
    private Set<Match> matches = new HashSet<>(0);
}
