package se.torgammelgard.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User - data to object.
 */
public class UserDto {

	@NotNull
	@Size(min=3)
	private String username;
	
	@NotNull
	@Size(min=3)
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	@NotNull
	@Size(min=3)
	private String password;
	
	@NotNull
	@Size(min=3)
	private String matchingPassword;

	public UserDto() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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
}
