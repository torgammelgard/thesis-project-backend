package se.torgammelgard.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@Data
public class Authority {

    @EmbeddedId
    private AuthorityPK authorityPK;

    @Embeddable
    @Data
    @AllArgsConstructor @NoArgsConstructor
    static class AuthorityPK implements Serializable {

        @NonNull
        @JoinColumn(table = "users", referencedColumnName = "username")
        protected String user;

        @NonNull
        protected String authority;

    }
}
