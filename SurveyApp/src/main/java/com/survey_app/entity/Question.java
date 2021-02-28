package com.survey_app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.survey_app.dto.AnswerDTO;

@Entity
@Table(name = "Question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "Interrogation")
	private String inter;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	// @JsonIgnore
	private List<Answer> answers;

	@ManyToOne
	@JoinColumn(name = "survey_id")
	@JsonIgnore
	private Survey survey;
	
	@Column(name = "require")
	private Boolean require;

	
	public Question() {
		super();
	}

	public Question(int id, String inter, Boolean require, List<AnswerDTO> answers) {
		super();
		this.id = id;
		this.inter = inter;
		this.require = require;
		answers.forEach(n->this.answers.add(new Answer(n.getId(), n.getResp(),n.getCounter())));
	}
	
	public Question(int id, String inter, Boolean require,  List<Answer> answers, Survey survey) {
		super();
		this.id = id;
		this.inter = inter;
		this.answers = answers;
		this.survey = survey;
		this.require = require;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {

		answers.forEach(n -> n.setQuestion(this));
		this.answers = answers;
	}

	public String getInter() {
		return inter;
	}

	public void setInter(String inter) {
		this.inter = inter;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", inter=" + inter + "]";
	}

	public Boolean getRequire() {
		return require;
	}

	public void setRequire(Boolean require) {
		this.require = require;
	}


}
