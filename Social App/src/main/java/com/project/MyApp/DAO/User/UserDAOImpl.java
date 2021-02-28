package com.project.MyApp.DAO.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.MyApp.entity.User;
import com.project.MyApp.entity.UserImages;

@Repository
public class UserDAOImpl implements UserDAO {

	// define field for entityManager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public UserDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<User> getUsers() {

		// Get the current session
		Session session = entityManager.unwrap(Session.class);

		// Create the query with all users from database
		Query<User> query = session.createQuery("from User order by username", User.class);

		// Convert query results to List
		List<User> users = query.getResultList();

		return users;
	}

	@Override
	public void saveUser(User theUser) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		UserImages images = new UserImages();
		session.save(images);
		theUser.setImages(images);
		// save or update the user
		session.saveOrUpdate(theUser);

	}

	@Override
	public User getUser(int theId) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		// read from database using primary key
		User theUser = session.get(User.class, theId);

		return theUser;

	}

	@Override
	public void deleteUser(int theId) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		// delete object using primary key
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("delete from User where id=:userFromId");

		// set userFromId to theId
		query.setParameter("userFromId", theId);

		// update the query
		query.executeUpdate();

	}

	@Override
	public User getUserByEmail(String email) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		// find that user who have the wanted email
		Query<User> query = session.createQuery("from User u where u.email = :email ", User.class);
		query.setParameter("email", email);
		try {
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User getUserByUsername(String username) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		// find that user who have the wanted email
		Query<User> query = session.createQuery("from User u where u.username = :username ", User.class);
		query.setParameter("username", username);
		try {
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}
	
	
	

	

}
