package com.survey_app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "question_id")
	@JsonIgnore
	private Question question;

	@Column(name = "response")
	private String resp;

	
	@Column(name = "answersCounter")
	int counter;
	
	
	
	
	public Answer() {
		super();
		
	}

	public Answer(int id, String resp, int counter) {
		super();
		this.id = id;
		this.resp = resp;
		this.counter = counter;
	}
	
	public Answer(int id, String resp) {
		super();
		this.id = id;
		this.resp = resp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public int getCounter() {
		return counter;
	}

	public void incrementCounter() {
		this.counter++;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "Answer [resp=" + resp + "]";
	}
	
	

}
