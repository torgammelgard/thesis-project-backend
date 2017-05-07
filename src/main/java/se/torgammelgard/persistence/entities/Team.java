package se.torgammelgard.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;

@Entity
@Table(name = "TEAMS")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 			// not sure if needed
public class Team implements Serializable {

	private static final long serialVersionUID = 146345634234L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", foreignKey = @ForeignKey(name = "OWNER_USER_ID_FK"))
    @JsonView(Views.Public.class)
    private User owner;

    @JsonView(Views.Public.class)
    private String teamName;

    @JsonView(Views.Public.class)
    private String playerOneName;

    @JsonView(Views.Public.class)
    private String playerTwoName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamOne", orphanRemoval = false)
    private List<Match> team1_matches = new ArrayList<>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamTwo", orphanRemoval = false)
    private List<Match> team2_matches = new ArrayList<>(0);

    public List<Match> getTeam1_matches() {
        return team1_matches;
    }

    public List<Match> getTeam2_matches() {
        return team2_matches;
    }


    public void addTeam1_match(Match match) {
        team1_matches.add(match);
    }

    public void addTeam2_match(Match match) {
        team2_matches.add(match);
    }

    public void removeTeam1_match(Match match) {
        team1_matches.remove(match);
        match.setTeamOne(null);
    }

    public void removeTeam2_match(Match match) {
        team2_matches.remove(match);
        match.setTeamTwo(null);
    }

	public void setTeam1_matches(List<Match> team1_matches) {
		this.team1_matches = team1_matches;
	}

	public void setTeam2_matches(List<Match> team2_matches) {
		this.team2_matches = team2_matches;
	}

	public Team() {
	}

	public Team(Long id, String teamName, String playerOneName, String playerTwoName) {
		super();
		this.id = id;
		this.teamName = teamName;
		this.playerOneName = playerOneName;
		this.playerTwoName = playerTwoName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPlayerOneName() {
		return playerOneName;
	}

	public void setPlayerOneName(String playerOneName) {
		this.playerOneName = playerOneName;
	}

	public String getPlayerTwoName() {
		return playerTwoName;
	}

	public void setPlayerTwoName(String playerTwoName) {
		this.playerTwoName = playerTwoName;
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + "]";
	}
}
