package se.torgammelgard.persistence.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 2780728471191820957L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(Views.Public.class)
    private String username;
    
    @JsonView(Views.Public.class)
    private String firstName;
    
    @JsonView(Views.Public.class)
    private String lastName;
    
    private String password;

    private Boolean enabled;

    @OneToMany(mappedBy = "owner")
    @JsonView(Views.Internal.class)
    private List<Team> teams;

    @ManyToMany
    @JoinTable(
    		name = "user_roles", 
    		joinColumns = @JoinColumn(referencedColumnName = "id"), 
    		inverseJoinColumns = @JoinColumn(referencedColumnName = "id")
    )
    private Collection<Role> roles;
    
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) {
	        return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	        return false;
	    }

	    User that = (User) o;

	    return getId() == that.getId();
	}

	@Override
	public int hashCode() {
	    return Long.valueOf(getId()).hashCode();
	}
}
