package com.project.MyApp.DAO.Friendship;

import java.util.List;

import com.project.MyApp.Enums.FriendshipStatusEnum;
import com.project.MyApp.entity.Friendship;
import com.project.MyApp.entity.User;

public interface FriendshipDAO {

	
	public void FriendRequest(User Requester, User Addressee);

	Friendship getRequest(User Requester, User Addressee);

	void deleteRequest(User Requester, User Addressee);

	List<Friendship> getFriends(User Requester, FriendshipStatusEnum status);

	public void acceptFriendRequest(User Addressee, User Requester);
	
	
}
