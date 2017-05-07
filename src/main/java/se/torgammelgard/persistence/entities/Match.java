package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MATCHES")
public class Match {

    @Id
    @GeneratedValue
    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private Boolean finished;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "team_1_id", foreignKey = @ForeignKey(name = "TEAM_1_ID_FK"))
    @JsonView(Views.Public.class)
    private Team teamOne;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "team_2_id", foreignKey = @ForeignKey(name = "TEAM_2_ID_FK"))
    @JsonView(Views.Public.class)
    private Team teamTwo;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "match_tennisset",
            joinColumns = {@JoinColumn(name = "match_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tennisset_id", referencedColumnName = "id")})
    @JsonView(Views.Public.class)
    private List<TennisSet> tennisSets = new ArrayList<>(0);

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "owner_user_id", foreignKey = @ForeignKey(name = "OWNER_USER_ID_FK"))
    @JsonView(Views.Public.class)
    private User owner;

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
        teamOne.addTeam1_match(this);
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
        teamOne.addTeam2_match(this);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TennisSet> getTennisSets() {
		return tennisSets;
	}

	public void setTennisSets(List<TennisSet> tennisSets) {
		this.tennisSets = tennisSets;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Team getTeamOne() {
		return teamOne;
	}

	public Team getTeamTwo() {
		return teamTwo;
	}

	public Boolean isFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
}
