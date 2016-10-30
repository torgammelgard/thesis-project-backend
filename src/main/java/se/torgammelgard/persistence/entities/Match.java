package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import se.torgammelgard.Views;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matches")
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long match_id;

    @JsonView(Views.Public.class)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonView(Views.Public.class)
    private Team teamOne;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonView(Views.Public.class)
    private Team teamTwo;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "matches")
    private List<TennisSet> tennisSets = new ArrayList<>(0);
}
