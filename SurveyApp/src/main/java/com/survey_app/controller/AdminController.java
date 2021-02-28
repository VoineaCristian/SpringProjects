package com.survey_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey_app.dto.SurveyDTO;
import com.survey_app.dto.UserDTO;
import com.survey_app.entity.Survey;
import com.survey_app.entity.User;
import com.survey_app.service.AnswerService;
import com.survey_app.service.QuestionService;
import com.survey_app.service.SurveyService;
import com.survey_app.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	SurveyService surveyService;

	@Autowired
	AnswerService answerService;

	@Autowired
	QuestionService questionService;

	@Autowired
	UserService userService;

	// redirect here after login as admin
	@GetMapping("")
	public ResponseEntity<String> adminHome() {
		return ResponseEntity.ok("ADMIN OK");
	}

	// return stats(Answers with number of selections) for every survey
	@GetMapping("/stats")
	public List<Survey> getStats() {

		return surveyService.list();

	}

	// return stats for a single survey
	@GetMapping("/stats/{id}")
	public ResponseEntity<Survey> getSpecificStats(@PathVariable int id) {

		Survey survey = surveyService.getSurveyById(id);
		if (survey == null) {
			return new ResponseEntity<>(survey, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(survey, HttpStatus.OK);

	}

	// Add new survey
	@PostMapping("/survey")
	public String addSurvey(@RequestBody SurveyDTO surveyDTO) {

		String surveyName = surveyDTO.getName();

		if (surveyService.alreadyExists(surveyName)) {
			return "Already Exists";
		}

		Survey survey = surveyDTO.convertToSurvey();
		surveyService.saveSurvey(survey);
		return "Succes!";
	}

	// Switch user role(i.e If he id ADMIN will become USER and vice-versa)
	@PutMapping("/change-role")
	public ResponseEntity<String> changeRole(@RequestBody UserDTO user, Authentication auth) {

		User getUser = userService.getUserByUsername(user.getUsername());
		if (getUser == null) {
			return ResponseEntity.badRequest().body("User not found");//If user doesn't exist
		} else if (getUser.getUsername().equals(auth.getName())) {
			return ResponseEntity.badRequest().body("You cannot change your role");
		} else {
			if (getUser.getRole().equals("ADMIN")) {
				getUser.setRole("USER");
			} else {
				getUser.setRole("ADMIN");
			}

			userService.saveUser(getUser);
			return ResponseEntity.ok("OK");
		}

	}

	//Print every register user registered user
	@GetMapping("/show-all-users")
	public List<UserDTO> showAllUsers() {

		List<User> users = userService.list();
		List<UserDTO> returnUsers = new ArrayList<>();

		users.forEach(n -> {

			n.setPassword(null);
			returnUsers.add(new UserDTO(n));

		});

		return returnUsers;
	}

	//switching between enable/disable survey's status
	@PutMapping("/change-survey-status/{id}")
	public ResponseEntity<HttpStatus> changeStatus(@PathVariable int id) {

		Survey survey = surveyService.getSurveyById(id);
		if (survey == null) {
			return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
		} else {
			survey.changeStatus();
			surveyService.saveSurvey(survey);
			return ResponseEntity.ok(HttpStatus.OK);
		}
	}
}
