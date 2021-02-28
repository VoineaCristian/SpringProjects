package com.survey_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.survey_app.entity.Answer;
import com.survey_app.entity.Question;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	 @Query("SELECT a FROM Answer a WHERE a.question = ?1")
    List<Answer> findAnswerByQuestion(Question q);

	 
}
