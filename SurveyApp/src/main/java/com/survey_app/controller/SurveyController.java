package com.survey_app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.survey_app.dto.SurveyDTO;
import com.survey_app.entity.Answer;
import com.survey_app.entity.Question;
import com.survey_app.entity.Survey;
import com.survey_app.entity.User;
import com.survey_app.service.AnswerService;
import com.survey_app.service.QuestionService;
import com.survey_app.service.SurveyService;
import com.survey_app.service.UserService;

@RestController
public class SurveyController {

	@Autowired
	SurveyService surveyService;

	@Autowired
	AnswerService answerService;

	@Autowired
	QuestionService questionService;

	@Autowired
	UserService userService;

	// mapping for normal user home
	@GetMapping("/")
	public ResponseEntity<String> succesUserLogin() {

		return ResponseEntity.ok("USER OK");
	}

	// returns every survey
	@GetMapping("/surveys")
	public List<SurveyDTO> getSurveys(Authentication auth) {

		List<Survey> surveys = surveyService.list();
		List<Survey> completedSurveys = userService.getUserByUsername(auth.getName()).getCompletedSurveys();
		List<SurveyDTO> returnSurveys = new ArrayList<>();
		String role = auth.getAuthorities().toString();

		// set field "completed" in survey, based on user's completed survey list
		surveys.forEach(n -> {
			SurveyDTO surveyDTO = new SurveyDTO(n, role);

			if (role.contains("ADMIN")) {
				returnSurveys.add(surveyDTO);
			} else {
				if (completedSurveys.contains(n)) {
					surveyDTO.setCompleted(true);
				} else
					surveyDTO.setCompleted(false);
				returnSurveys.add(surveyDTO);
			}

		});

		return returnSurveys;

	}

	// returns only that survey which is selected
	@GetMapping("/surveys/{id}")
	public SurveyDTO getSurvey(HttpServletResponse response, @PathVariable int id, Authentication auth) {

		Survey survey = surveyService.getSurveyById(id);
		String role = auth.getAuthorities().toString();
		User user = userService.getUserByUsername(auth.getName());
		
		/*
		 * based on role, will hide "counter" field, based on user's answer will return
		 * only his self answer
		 */
		if (user.getCompletedSurveys().contains(survey)) {

			SurveyDTO surveyDTO = new SurveyDTO();

			return surveyDTO.completeSurvey(survey, role, user.getAnswers());

		}
		if (survey == null) {
			try {
				response.sendRedirect("/surveys");// redirect back to surveys
			} catch (IOException e) {

				e.printStackTrace();
			}
			return null;
		}

		return new SurveyDTO(survey, role);
	}

	/*
	 * mapping for completing survey form. Form allow only whole completed survey
	 * You can complete a survey once
	 */
	@PostMapping("/surveys/{id}")
	public ResponseEntity<String> completeSurvey(@RequestBody() Map<Integer, Integer> listOfAnswers,
			@PathVariable int id, Authentication auth) {

		Survey survey = surveyService.getSurveyById(id);
		if(survey == null) {
			return ResponseEntity.badRequest().body("Not Found");
		}
		User user = userService.getUserByUsername(auth.getName());
		List<Answer> answers = new ArrayList<>();
		int numberOfQuestions = survey.getQuestions().size();
		
		if (!survey.isEnable()) {
			return ResponseEntity.badRequest().body("This survey is unavailable");
		}
		
		if (numberOfQuestions < listOfAnswers.size()) {
			return ResponseEntity.badRequest().body("too many answers");
		} else if (user.getCompletedSurveys().contains(survey)) {
			return ResponseEntity.badRequest().body("You have already submitted this survey ");
		} else {

			for (int i = 0; i < survey.getQuestions().size(); i++) {

				Integer getValue = listOfAnswers.get(i);
				Question q = survey.getQuestions().get(i);

				if (getValue != null) {

					if (getValue >= q.getAnswers().size() || getValue < 0) {
						return ResponseEntity.badRequest().body("Invalid answer for question: " + i);
					}
					Answer an = q.getAnswers().get(getValue);
					
					an.incrementCounter();
					answers.add(an);
					
				}else if(q.getRequire().booleanValue()) {
					return ResponseEntity.badRequest().body("You Must Answer The Require Qustions " + i);
				}
			}

			user.addAnswers(answers);
			user.addCompletedSurvey(survey);
			userService.saveUser(user);
			return ResponseEntity.ok("OK");
		}

	}

}
