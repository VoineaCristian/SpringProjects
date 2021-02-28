package com.survey_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.survey_app.entity.Question;
import com.survey_app.entity.Survey; 

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	 @Query("SELECT q FROM Question q WHERE q.survey = ?1")
	    List<Question> findQuestionById(Survey surveyId);
}
