package se.torgammelgard.persistence.entities;

import com.fasterxml.jackson.annotation.JsonView;
import se.torgammelgard.Views;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private long id;

    @Column
    @JsonView(Views.Public.class)
    private String name;

    @Version
    private long version;

    public Game() {
    }

    public Game(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return String.format("Game name : %s", getName());
    }
}
