package se.torgammelgard.persistence.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;

/**
 * An entity class for storing tennis matches.
 * 
 * @author torgammelgard
 *
 */
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
    
    @JsonView(Views.Public.class)
    private Date date;
    
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
    	if (teamOne != null) {
    		this.teamOne = teamOne;
    		teamOne.addTeam1_match(this);
    	}
    }

    public void setTeamTwo(Team teamTwo) {
    	if (teamTwo != null) {
    		this.teamTwo = teamTwo;
    		teamOne.addTeam2_match(this);
    	}
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
		Collections.sort(tennisSets, new Comparator<TennisSet>() {

			@Override
			public int compare(TennisSet set1, TennisSet set2) {
				return set1.getSetNumber() - set2.getSetNumber();
			}
			
		});
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
	
	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) {
	        return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	        return false;
	    }

	    Match that = (Match) o;

	    return getId() == that.getId();
	}

	@Override
	public int hashCode() {
	    return Long.valueOf(getId()).hashCode();
	}
}
