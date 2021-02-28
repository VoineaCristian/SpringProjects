package com.survey_app.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey_app.entity.User;
import com.survey_app.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }
    
    public void saveUser(User u) {
    	userRepository.save(u);
    }
    
    public User getUserByUsername(String username) {
    	return userRepository.findByUsername(username);
    }
    
    public boolean alreadyExists(String username) {
    	
    	return userRepository.existsByUsername(username);
    }

    
}