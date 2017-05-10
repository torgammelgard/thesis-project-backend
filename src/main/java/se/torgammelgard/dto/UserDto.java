package se.torgammelgard.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

	@NotNull
	@Size(min=3)
	private String username;
	
	@NotNull
	@Size(min=3)
	private String email;
	
	@NotNull
	@Size(min=3)
	private String password;
	private String mastchingPassword;

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

	public String getMastchingPassword() {
		return mastchingPassword;
	}

	public void setMastchingPassword(String mastchingPassword) {
		this.mastchingPassword = mastchingPassword;
	}
}
