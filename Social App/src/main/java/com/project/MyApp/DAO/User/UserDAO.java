package com.project.MyApp.DAO.User;

import java.util.List;

import com.project.MyApp.entity.User;

public interface UserDAO {

	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);
	
	public User getUserByEmail(String email);

	User getUserByUsername(String username);
	
	


}
