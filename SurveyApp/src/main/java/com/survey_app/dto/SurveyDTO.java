package com.survey_app.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.survey_app.entity.Answer;
import com.survey_app.entity.Question;
import com.survey_app.entity.Survey;

@JsonInclude(value = Include.NON_NULL)
public class SurveyDTO {

	private int id;

	private String name;

	private List<QuestionDTO> questions;

	private Boolean enable;

	private Boolean completed;

	public SurveyDTO() {
		super();
	}

	/*
	 * build a surveyDTO based on Survey entity
	 */
	public SurveyDTO(Survey survey, String role) {

		this.id = survey.getId();
		this.name = survey.getName();
		this.questions = new ArrayList<>();
		this.enable = survey.isEnable();
		this.completed = null;

		survey.getQuestions().forEach(n -> this.questions.add(new QuestionDTO(n, role)));

	}

	/*
	 * build a surveyDTO, set survey as completed SurveyDTO will contains only
	 * user's answers
	 */
	public SurveyDTO completeSurvey(Survey survey, String role, List<Answer> answers) {
		this.id = survey.getId();
		this.name = survey.getName();
		this.questions = new ArrayList<>();
		this.enable = survey.isEnable();
		this.completed = true;

		answers.forEach(n -> {
			if (n.getQuestion().getSurvey().getId() == survey.getId()) {
				QuestionDTO questionDTO = new QuestionDTO();
				this.questions.add(questionDTO.completedQuestion(n.getQuestion(), n, role));
			}
		});

		return this;

	}

	/*
	 * build Survey entity based on surveyDTO
	 */
	public Survey convertToSurvey() {

		Survey survey = new Survey();
		survey.setId(this.id);
		survey.setName(this.name);
		survey.setEnable(this.getEnable());
		List<Question> questionsConverted = new ArrayList<>();

		this.questions.forEach(n -> {
			Question q = n.convertToQuestion();
			q.setSurvey(survey);
			questionsConverted.add(q);

		});

		survey.setQuestions(questionsConverted);
		return survey;

	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "SurveyDTO [id=" + id + ", name=" + name + ", questions=" + questions + ", enable=" + enable + "]";
	}

}
