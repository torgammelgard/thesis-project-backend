package se.torgammelgard.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matches")
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long match_id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Team teamOne;

    @ManyToOne(fetch = FetchType.EAGER)
    private Team teamTwo;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "matches")
    private List<TennisSet> tennisSets = new ArrayList<>(0);
}
