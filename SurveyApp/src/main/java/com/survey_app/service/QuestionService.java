package com.survey_app.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey_app.entity.Question;
import com.survey_app.entity.Survey;
import com.survey_app.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> list() {
        return questionRepository.findAll();
    }
    
     List<Question> findQuestionById(Survey survey) {
    	return questionRepository.findQuestionById(survey);
	}
     
     public void saveQuestion(Question q) {
    	 questionRepository.save(q);
     }
     
     public void saveQuestions(List<Question> qs) {
    	 questionRepository.saveAll(qs);
     }
}