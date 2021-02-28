package com.project.MyApp.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MyApp.DAO.EmailToken.EmailTokenDAO;
import com.project.MyApp.DAO.Friendship.FriendshipDAOImpl;
import com.project.MyApp.DAO.Photos.PhotosDAO;
import com.project.MyApp.DAO.User.UserDAO;
import com.project.MyApp.Enums.FriendshipStatusEnum;
import com.project.MyApp.entity.Friendship;
import com.project.MyApp.entity.User;

@Service
public class UserServiceImpl implements UserService {

	
	//need to inject userDAO
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	EmailTokenDAO TDAo;
	
	@Autowired
	PhotosDAO photosDAO;
	
	@Autowired
	FriendshipDAOImpl friendshipDAO;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		
		return userDAO.getUsers();
		
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {
		userDAO.saveUser(theUser);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {
		
		userDAO.deleteUser(theId);
		
	}
	
	@Override
	@Transactional
	public User getUserByEmail(String email) {
		
		return userDAO.getUserByEmail(email);
		
	}
	
	@Override
	@Transactional
	public User getUserByUsername(String username) {
		
		return userDAO.getUserByUsername(username);
		
	}
	
	@Override
	@Transactional
	public void setProfileImage(String username, String path) {
		
		photosDAO.setProfilePhoto(username, path);
		
	}
	
	@Override
	@Transactional
	public void addPhotos(String username, List<String> paths) {
		
		photosDAO.addPhotos(username, paths);
		
	}
	
	@Override
	@Transactional
	public void deletePhoto(String path) {
		
		photosDAO.deletePhoto(path);
		
	}
	
	@Override
	@Transactional
	public void sendFriendRequest(User requester, User adressee) {
		
		friendshipDAO.FriendRequest(requester, adressee);
		
	}
	
	@Override
	@Transactional
	public Friendship getRequest(User Requester, User Addressee) {
		
		return friendshipDAO.getRequest(Requester, Addressee);
		
	}
	
	@Override
	@Transactional
	public void deleteFriendRequest(User Requester, User Addressee) {
		
		friendshipDAO.deleteRequest(Requester, Addressee);
		
	}
	
	@Override
	@Transactional
	public List<Friendship> getFriends(User Requester, FriendshipStatusEnum stat) {

		return friendshipDAO.getFriends(Requester, stat);
	}
	
	@Override
	@Transactional
	public void acceptFriendRequest(User Addressee, User Requester) {

		friendshipDAO.acceptFriendRequest(Addressee, Requester);
	}
	
	@Override
	@Transactional
	public void deleteFriend(User Addressee, User Requester) {

		friendshipDAO.deleteRequest(Requester, Addressee);
		friendshipDAO.deleteRequest(Addressee, Requester);
	}
	
	
	

}
