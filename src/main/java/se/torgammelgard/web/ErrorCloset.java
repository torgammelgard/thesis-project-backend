package se.torgammelgard.web;

public class ErrorCloset {

	private Boolean username_exists;
	private Boolean password_mismatch;
	
	public ErrorCloset() {
	}

	public Boolean getUsername_exists() {
		return username_exists;
	}

	public void setUsername_exists(Boolean username_exists) {
		this.username_exists = username_exists;
	}

	public Boolean getPassword_mismatch() {
		return password_mismatch;
	}

	public void setPassword_mismatch(Boolean password_mismatch) {
		this.password_mismatch = password_mismatch;
	}
	
	
}
