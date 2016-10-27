package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.torgammelgard.Views;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long team_id;

    @JsonView(Views.Public.class)
    private String teamName;

    @JsonView(Views.Public.class)
    private String playerOneName;

    @JsonView(Views.Public.class)
    private String playerTwoName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Match> matches = new HashSet<>(0);
}
