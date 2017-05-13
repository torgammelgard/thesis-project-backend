package se.torgammelgard.dto;

import javax.validation.constraints.NotNull;

// TODO do we need this? fix login checking then

public class LoginDto {

	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	public LoginDto() {
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
