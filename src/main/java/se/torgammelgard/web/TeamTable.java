package se.torgammelgard.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import se.torgammelgard.persistence.entities.Team;

/**
 * A helper class for storing information about which teams that have been selected in the table on the web page.
 * 
 * @author torgammelgard
 *
 */
@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class TeamTable {

    @NonNull
    private List<Team> teams;
    private Long[] selectedTeams;
}
