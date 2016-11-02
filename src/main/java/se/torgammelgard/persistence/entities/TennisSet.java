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
@Table(name = "TENNISSETS")
@Data
public class TennisSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tennisset_tennissetscore",
            joinColumns = @JoinColumn(name = "tennisset_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tennissetscore_id", referencedColumnName = "id"))
    private List<TennisSetScore> tennisSetScore = new ArrayList<>(0);

    @ManyToMany(mappedBy = "tennisSets", cascade = {CascadeType.ALL})
    private List<Match> matches = new ArrayList<>(0);
}
