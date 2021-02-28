package com.project.MyApp.Utils;

public class NewPassword {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String password;
	private String confirmPassword;

	@Override
	public String toString() {
		return "NewPassword [username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public boolean verifyMatch(){
		
		return this.password.equals(this.confirmPassword);
	}

}
