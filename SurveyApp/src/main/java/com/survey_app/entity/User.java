package com.survey_app.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@ManyToMany
	@Column(name = "completedSurveyes")
	private List<Survey> completedSurveys;

	@Column(name = "registration_date", columnDefinition = "TIMESTAMP")
	private Timestamp registrationDate;

	@Column(name = "answers")
	@ManyToMany
	private List<Answer> answers;

	public User() {
		super();

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

	public List<Survey> getCompletedSurveys() {
		return completedSurveys;
	}

	public void setCompletedSurveys(List<Survey> completedSurveys) {
		this.completedSurveys = completedSurveys;
	}

	public void addCompletedSurvey(Survey survey) {
		this.completedSurveys.add(survey);
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp timestamp) {
		this.registrationDate = timestamp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswers(List<Answer> answers) {

		this.answers.addAll(answers);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", completedSurveys=" + ", registrationDate=" + "]";
	}

}