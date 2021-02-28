package com.survey_app.dto;

import java.util.ArrayList;
import java.util.List;

import com.survey_app.entity.Answer;
import com.survey_app.entity.Question;

public class QuestionDTO {

	private int id;

	private String inter;

	private List<AnswerDTO> answers;

	private Boolean require;

	public QuestionDTO() {
		super();
	}

	/*
	 * build QuetionDTO based on Question and role if role is USER, will hide
	 * counter
	 */
	public QuestionDTO(Question q, String role) {

		this.id = q.getId();
		this.answers = new ArrayList<>();
		this.inter = q.getInter();
		this.require = q.getRequire();
		if (role.contains("USER")) {
			q.getAnswers().forEach(n -> {
				n.setCounter(-1);
				this.answers.add(new AnswerDTO(n));
			});
		} else {
			q.getAnswers().forEach(n -> this.answers.add(new AnswerDTO(n)));
		}

	}

	/*
	 * Build QuestionDTO basen on completed survey with specific answer
	 */
	public QuestionDTO completedQuestion(Question q, Answer answer, String role) {

		this.id = q.getId();
		this.answers = new ArrayList<>();
		this.require = q.getRequire();
		if (role.contains("USER")) {
			answer.setCounter(-1);
		}
		this.answers.add(new AnswerDTO(answer));
		this.inter = q.getInter();

		return this;
	}

	/*
	 * convert QuestionDTO to Question Entity
	 */
	public Question convertToQuestion() {
		Question question = new Question();
		question.setId(this.id);
		question.setInter(this.inter);
		question.setRequire(this.require);
		List<Answer> answerList = new ArrayList<>();
		this.answers.forEach(n -> {

			Answer an = n.convertToAnswer();
			an.setQuestion(question);
			answerList.add(an);
		});

		question.setAnswers(answerList);
		return question;
	}

	@Override
	public String toString() {
		return "QuestionDTO [id=" + id + ", inter=" + inter + ", answers=" + answers + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInter() {
		return inter;
	}

	public void setInter(String inter) {
		this.inter = inter;
	}

	public List<AnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}

	public Boolean getRequire() {
		return require;
	}

	public void setRequire(Boolean require) {
		this.require = require;
	}

}
