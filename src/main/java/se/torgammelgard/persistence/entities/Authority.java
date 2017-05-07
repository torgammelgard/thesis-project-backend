package se.torgammelgard.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUTHORITIES")
public class Authority {

    @EmbeddedId
    private AuthorityPK authorityPK;

    @Embeddable
    static class AuthorityPK implements Serializable {

		private static final long serialVersionUID = -1364734397652503953L;

        @JoinColumn(table = "users", referencedColumnName = "username")
        protected String user;

        protected String authority;

		public AuthorityPK() {
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
			this.authority = authority;
		}

    }
}
