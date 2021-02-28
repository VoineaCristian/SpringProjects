package com.survey_app.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.survey_app.dto.UserDTO;
import com.survey_app.entity.User;
import com.survey_app.service.UserService;

@RestController
public class AuthController {

	@Autowired
	UserService userService;

	//mapping for registration
	@PostMapping("/registration")
	public ResponseEntity<String> sendRegistrationForm(@RequestBody UserDTO userDTO) {

		if (userService.alreadyExists(userDTO.getUsername())) {

			return ResponseEntity.badRequest().body("Username Already Exists");
		}
		
		User user = userDTO.convertToUser();
		user.setRole("USER");
		user.setPassword("{noop}" + user.getPassword());
		user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
		userService.saveUser(user);

		return ResponseEntity.ok("OK");

	}
	
	//mapping for bad credentials request
	@GetMapping("/bad-credentials")
	public ResponseEntity<String> succesUserLogin() {

		return ResponseEntity.badRequest().body("Bad Credentials");
	}
}
