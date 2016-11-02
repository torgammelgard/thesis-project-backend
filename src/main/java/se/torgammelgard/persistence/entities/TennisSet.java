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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tennisset_tennissetscore",
            joinColumns = @JoinColumn(name = "tennisset_id", referencedColumnName = "tennisset_id"),
            inverseJoinColumns = @JoinColumn(name = "setscore_id", referencedColumnName = "setscore_id"))
    private List<TennisSetScore> tennisSetScore = new ArrayList<>(0);

    @ManyToMany(mappedBy = "tennisSets", cascade = {CascadeType.ALL})
    private List<Match> matches = new ArrayList<>(0);
}
