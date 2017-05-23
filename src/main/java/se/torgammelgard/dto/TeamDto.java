package se.torgammelgard.dto;

/**
 * Team - data to object.
 */
import javax.validation.constraints.NotNull;

import se.torgammelgard.persistence.entities.Team;

public class TeamDto {
	
	public TeamDto() {
		super();
	}

	private Long id;
	
	@NotNull
	private String teamName;
	
	private String playerOneName;
	
	private String playerTwoName;

	public static TeamDto build(Team team) {
		TeamDto teamDto = new TeamDto();
		teamDto.setId(team.getId());
		teamDto.setTeamName(team.getTeamName());
		teamDto.setPlayerOneName(team.getPlayerOneName());
		teamDto.setPlayerTwoName(team.getPlayerTwoName());
		return teamDto;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
