package se.torgammelgard.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.persistence.entities.User;

public class MatchDto {

	private Long id;
	
    private String name;

    private Boolean finished;
    
    private Team teamOne;
    
    private Team teamTwo;

    private List<TennisSet> tennisSets = new ArrayList<>(0);

    private User owner;

	public MatchDto() {
		List<TennisSet> tennisSets = new ArrayList<TennisSet>();
		for (int i = 1; i <= 5; i++) {
			TennisSet tennisSet = new TennisSet();
			tennisSet.setSetNumber(i);
			TennisSetScore tss = new TennisSetScore();
			tss.setScoreTeamOne(0);
			tss.setScoreTeamTwo(0);
			tennisSet.setTennisSetScore(tss);
			tennisSets.add(tennisSet);
		}
		this.tennisSets = tennisSets;
	}
	public static MatchDto build(Match match) {
		MatchDto matchDto = new MatchDto();
		matchDto.setId(match.getId());
		matchDto.setName(match.getName());
		matchDto.setFinished(match.getFinished());
		matchDto.setOwner(match.getOwner());
		matchDto.setTeamOne(match.getTeamOne());
		matchDto.setTeamTwo(match.getTeamTwo());
		matchDto.setTennisSets(null);
		List<TennisSet> ts = new ArrayList<TennisSet>();
		
		for (TennisSet tennisSet : match.getTennisSets()) {
			ts.add(tennisSet);
		}
		
		matchDto.setTennisSets(ts);
		return matchDto;
	}
	
	// convert to Match method
	public Match convertToMatch() {
		Match match = new Match();
		match.setId(this.getId());
		match.setName(this.getName());
		match.setFinished(this.getFinished());
		match.setTeamOne(this.getTeamOne());
		match.setTeamTwo(this.getTeamTwo());
		match.setTennisSets(this.getTennisSets());
		match.setOwner(this.owner);
		return match;
	}
	
	// getters and setters
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

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public Team getTeamOne() {
		return teamOne;
	}

	public void setTeamOne(Team teamOne) {
		this.teamOne = teamOne;
	}

	public Team getTeamTwo() {
		return teamTwo;
	}

	public void setTeamTwo(Team teamTwo) {
		this.teamTwo = teamTwo;
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

}
