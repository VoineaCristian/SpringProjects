package com.survey_app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.survey_app.config.NonNegativeNumber;
import com.survey_app.entity.Answer;

public class AnswerDTO {

	private Integer id;

	private String resp;

	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = NonNegativeNumber.class)
	private Integer counter = 0;

	public AnswerDTO() {
		super();

	}

	/* build AnswerDTO */
	public AnswerDTO(Answer answer) {

		super();
		this.id = answer.getId();
		this.resp = answer.getResp();
		this.counter = answer.getCounter();
	}

	/* convert AnswerDTO to Answer */
	public Answer convertToAnswer() {

		Answer answer = new Answer();
		answer.setResp(this.resp);
		return answer;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "AnswerDTO [id=" + id + ", resp=" + resp + ", counter=" + counter + "]";
	}

}
