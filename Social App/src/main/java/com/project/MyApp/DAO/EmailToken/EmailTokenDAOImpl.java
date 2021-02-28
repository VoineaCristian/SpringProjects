package com.project.MyApp.DAO.EmailToken;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.MyApp.entity.EmailToken;


@Repository
public class EmailTokenDAOImpl implements EmailTokenDAO {

	
	// define field for entityManager	
		private EntityManager entityManager;
			
		// set up constructor injection
		@Autowired
		public EmailTokenDAOImpl(EntityManager theEntityManager) {
			entityManager = theEntityManager;
		}
		
	
	
	@Override
	public List<EmailToken> getEmailTokens() {
		
		//Get the current session
		Session session = entityManager.unwrap(Session.class);
		
		//Create the query with all EmailTokens from database
		Query<EmailToken> query = session.createQuery("from EmailToken order by EmailTokenname",EmailToken.class);
		
		//Convert query results to List
		List<EmailToken> EmailTokens = query.getResultList();
		
		return EmailTokens;
	}

	@Override
	public void saveEmailToken(EmailToken theEmailToken) {
	
		//get current session
		Session session = entityManager.unwrap(Session.class);
		
		//save or update the EmailToken
		session.saveOrUpdate(theEmailToken);
		
		
	}

	@Override
	public EmailToken getEmailToken(int theId) {
		
		//get current session
		Session session = entityManager.unwrap(Session.class);
		
		//read from database using primary key
		EmailToken theEmailToken = session.get(EmailToken.class, theId);
		
		return theEmailToken;
		
	
	}

	@Override
	public void deleteEmailToken(int theId) {
		
		//get current session
		Session session = entityManager.unwrap(Session.class);
		
		//delete object using primary key
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("delete from EmailToken where id=:EmailTokenFromId");
		
		//set EmailTokenFromId to theId
		query.setParameter("EmailTokenFromId", theId);
		
		//update the query
		query.executeUpdate();
		
	}
	
	@Override
	public EmailToken getByConfirmationToken(String cf) {
		
		//get current session
		Session session = entityManager.unwrap(Session.class);
		
		//find that user who have the wanted email 
		Query<EmailToken> query = session.createQuery("from EmailToken t where t.confirmationToken = :CF ", EmailToken.class);
		query.setParameter("CF", cf);
		

		try {
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
		
		
		
		
	}

}
