package com.survey_app.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.survey_app.entity.User;

@JsonInclude(value = Include.NON_NULL)
public class UserDTO {

	private int id;

	private String username;

	private String password;

	private String role;

	private Timestamp registrationDate;

	public UserDTO() {
		super();
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.role = user.getRole();
		this.password = user.getPassword();
		this.registrationDate = user.getRegistrationDate();
	}

	public User convertToUser() {

		User user = new User();
		user.setPassword(this.password);
		user.setUsername(this.username);
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

}
