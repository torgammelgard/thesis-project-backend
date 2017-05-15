package se.torgammelgard.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;

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

    @OneToMany(mappedBy = "tennisSetScore", cascade = CascadeType.ALL)
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
