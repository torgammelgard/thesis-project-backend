package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import se.torgammelgard.Views;

import javax.persistence.*;

@Entity
@Table(name = "GAMES")
@Data
@NoArgsConstructor @RequiredArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private long id;

    @JsonView(Views.Public.class)
    @NonNull
    private String name;

    @Version
    private long version;

}
