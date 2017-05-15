package se.torgammelgard.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;

@Entity
@Table(name = "TENNISSETS")
public class TennisSet {

    @Id
    @GeneratedValue()
    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Public.class)
    private Long length;
    
    @JsonView(Views.Public.class)
    private int setNumber;
    
//    @JsonView(Views.Public.class)
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "tennisset_tennissetscore",
//            joinColumns = @JoinColumn(name = "tennisset_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "tennissetscore_id", referencedColumnName = "id"))
//    private List<TennisSetScore> tennisSetScore = new ArrayList<>(0);

    @JsonView(Views.Public.class)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tennissetscore_id", foreignKey = @ForeignKey(name = "TENNISSETSCORE_ID_FK"))
    private TennisSetScore tennisSetScore;
    
    @ManyToMany(mappedBy = "tennisSets", cascade = {CascadeType.ALL})
    private List<Match> matches = new ArrayList<>(0);

	public TennisSet() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TennisSetScore getTennisSetScore() {
		return tennisSetScore;
	}

	public void setTennisSetScore(TennisSetScore tennisSetScore) {
		this.tennisSetScore = tennisSetScore;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public int getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(int i) {
		this.setNumber = i;
	}

}
