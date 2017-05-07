package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import se.torgammelgard.Views;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TENNISSETSCORES")
public class TennisSetScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Public.class)
    private int scoreTeamOne;

    @JsonView(Views.Public.class)
    private int scoreTeamTwo;

    @ManyToMany(mappedBy = "tennisSetScore", cascade = CascadeType.ALL)
    private Set<TennisSet> tennisSets = new HashSet<>(0);

	public TennisSetScore() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScoreTeamOne() {
		return scoreTeamOne;
	}

	public void setScoreTeamOne(int scoreTeamOne) {
		this.scoreTeamOne = scoreTeamOne;
	}

	public int getScoreTeamTwo() {
		return scoreTeamTwo;
	}

	public void setScoreTeamTwo(int scoreTeamTwo) {
		this.scoreTeamTwo = scoreTeamTwo;
	}

	public Set<TennisSet> getTennisSets() {
		return tennisSets;
	}

	public void setTennisSets(Set<TennisSet> tennisSets) {
		this.tennisSets = tennisSets;
	}
}
