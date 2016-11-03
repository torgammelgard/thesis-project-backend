package se.torgammelgard.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @NonNull
    private Boolean enabled;

    @OneToMany(mappedBy = "owner")
    private List<Team> teams;
}
