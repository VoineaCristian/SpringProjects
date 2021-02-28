package com.survey_app.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey_app.entity.Answer;
import com.survey_app.entity.Question;
import com.survey_app.repository.AnswerRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> list() {
        return answerRepository.findAll();
    }
    
    public List<Answer> findAnswerByQuestion(Question q) {
    	return answerRepository.findAnswerByQuestion(q);
    }
    
    public void saveAnswer(Answer a) {
    	answerRepository.save(a);
    }
    
   
}