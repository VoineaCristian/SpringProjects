package com.project.MyApp.Service.User;

import java.util.List;

import com.project.MyApp.Enums.FriendshipStatusEnum;
import com.project.MyApp.entity.Friendship;
import com.project.MyApp.entity.User;


public interface UserService {

	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);
	
	public User getUserByEmail(String email);

	User getUserByUsername(String username);

	void setProfileImage(String username, String path);

	void addPhotos(String username, List<String> paths);

	void deletePhoto(String path);
	
	void sendFriendRequest(User requester, User adressee);

	Friendship getRequest(User Requester, User Addressee);

	void deleteFriendRequest(User Requester, User Addressee);


	List<Friendship> getFriends(User Requester, FriendshipStatusEnum stat);

	public void acceptFriendRequest(User Addressee, User Requester);

	void deleteFriend(User Addressee, User Requester);

}
