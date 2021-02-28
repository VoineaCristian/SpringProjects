package com.survey_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey_app.entity.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

	List<Survey> findByName(String name);
}
