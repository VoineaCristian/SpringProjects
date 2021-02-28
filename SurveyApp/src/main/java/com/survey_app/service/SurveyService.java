package com.survey_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey_app.entity.Survey;
import com.survey_app.repository.SurveyRepository;

@Service
public class SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;

	public List<Survey> list() {
		return surveyRepository.findAll();
	}

	public Survey getSurveyById(int id) {
		return surveyRepository.findById(id).orElse(null);
	}
	
	public void saveSurvey(Survey survey) {
		surveyRepository.save(survey);
	}
	
	public List<Survey> getSurveyByName(String name) {
		return surveyRepository.findByName(name);
	}
	
	public boolean alreadyExists(String name) {
		
		return !surveyRepository.findByName(name).isEmpty(); 
	}
}