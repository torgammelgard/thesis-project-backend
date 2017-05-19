package se.torgammelgard.dto;

import javax.validation.constraints.NotNull;

public class TeamDto {
	
	public TeamDto() {
		super();
	}

	@NotNull
	private String teamName;
	
	private String playerOneName;
	
	private String playerTwoName;

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
	
}
