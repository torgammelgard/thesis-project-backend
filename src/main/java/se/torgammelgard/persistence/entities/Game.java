package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.beans.factory.annotation.Required;
import se.torgammelgard.Views;

import javax.persistence.*;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor @RequiredArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private long id;

    @Column
    @JsonView(Views.Public.class)
    @NonNull
    private String name;

    @Version
    private long version;

}
