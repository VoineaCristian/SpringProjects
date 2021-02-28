package com.project.MyApp.DAO.Friendship;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.MyApp.DAO.User.UserDAO;
import com.project.MyApp.Enums.FriendshipStatusEnum;
import com.project.MyApp.entity.Friendship;
import com.project.MyApp.entity.User;

@Repository
public class FriendshipDAOImpl implements FriendshipDAO {

	// define field for entityManager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public FriendshipDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public void FriendRequest(User Requester, User Addressee) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		Friendship request = new Friendship(Requester, Addressee, FriendshipStatusEnum.NotAccepted);
		// save or update the user
		session.saveOrUpdate(request);

	}

	@Override
	public void acceptFriendRequest(User Addressee, User Requester) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		Friendship request = this.getRequest(Requester, Addressee);
		Friendship requestReverse = new Friendship(Addressee, Requester, FriendshipStatusEnum.Friends);
		request.setStatus(FriendshipStatusEnum.Friends);
		
		// save or update the user
		session.saveOrUpdate(request);
		session.saveOrUpdate(requestReverse);
		
		

	}

	@Override
	public Friendship getRequest(User Requester, User Addressee) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		Friendship request = new Friendship(Requester, Addressee, FriendshipStatusEnum.NotAccepted);
		// save or update the user
		Query<Friendship> query = session
				.createQuery("from Friendship f where f.requester = :req and f.addressee = :addr  ", Friendship.class);
		query.setParameter("req", Requester);
		query.setParameter("addr", Addressee);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public void deleteRequest(User Requester, User Addressee) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		// delete object using primary key
		@SuppressWarnings("unchecked")
		Query<Friendship> query = session
				.createQuery("delete from Friendship f where f.requester = :req and f.addressee = :addr ");
		query.setParameter("req", Requester);
		query.setParameter("addr", Addressee);

		// update the query
		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friendship> getFriends(User Requester, FriendshipStatusEnum status) {

		// get current session
		Session session = entityManager.unwrap(Session.class);
		Query<Friendship> query= null;
		// delete object using primary key

		if (status.equals(FriendshipStatusEnum.Friends)) {
			
			
			query = session.createQuery("from Friendship f where f.addressee = :req and f.status = :stat ");
			query.setParameter("req", Requester);
			query.setParameter("stat", status);
			
		} else if (status.equals(FriendshipStatusEnum.NotAccepted)) {
			
			query = session.createQuery("from Friendship f where f.addressee = :addr and f.status = :stat ");
			query.setParameter("addr", Requester);
			query.setParameter("stat", status);
		}

		// Convert query results to List
		List<Friendship> friends = query.getResultList();

		return friends;

	}

}
