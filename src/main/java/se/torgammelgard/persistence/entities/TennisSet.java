package se.torgammelgard.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import se.torgammelgard.Views;

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
