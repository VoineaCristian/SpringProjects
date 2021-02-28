package com.survey_app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.survey_app.dto.QuestionDTO;

@Entity
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "SurveyName")
	private String name;

	@OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
	private List<Question> questions;

	@Column(name = "enable")

	private boolean enable;

	public Survey() {

	}

	public Survey(int id, String name, List<QuestionDTO> questions) {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestionsParent() {
		this.questions.forEach(n -> n.setSurvey(this));
	}

	public void setQuestions(List<Question> questions) {

		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Survey [id=dfdsf" + id + ", name=" + name + "]";
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void changeStatus() {
		if (this.enable ) {
			this.enable = false;
		} else
			this.enable = true;
	}
}
