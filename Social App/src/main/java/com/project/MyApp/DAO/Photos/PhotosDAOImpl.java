package com.project.MyApp.DAO.Photos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.MyApp.DAO.User.UserDAO;
import com.project.MyApp.DAO.User.UserDAOImpl;
import com.project.MyApp.Service.User.UserService;
import com.project.MyApp.entity.Photo;

import com.project.MyApp.entity.User;
import com.project.MyApp.entity.UserImages;

@Repository
public class PhotosDAOImpl implements PhotosDAO {

	// define field for entityManager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public PhotosDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Autowired
	private UserDAO userDAO;

	@Override
	public void addPhotos(String username, List<String> paths) {

		Session session = entityManager.unwrap(Session.class);
		List<Photo> newPhotos = new ArrayList<Photo>();
		User user = userDAO.getUserByUsername(username);

		UserImages imgDb = user.getImages();

		for (String path : paths) {
			Photo photo = new Photo(path, imgDb);
			imgDb.setImageCount(imgDb.getImageCount() + 1);
			newPhotos.add(photo);
		}

		imgDb.addPhotos(newPhotos);
		session.save(imgDb);

	}

	@Override
	public void setProfilePhoto(String username, String path) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		User user = userDAO.getUserByUsername(username);

		if (user.getImages() == null) {
			UserImages img = new UserImages();
			session.save(img);
			user.setImages(img);

		}

		System.out.println(path);
		user.getImages().setProfilePhoto(path);
		session.saveOrUpdate(user);

	}

	@Override
	public void deletePhoto(String path) {

		// get current session
		Session session = entityManager.unwrap(Session.class);

		// delete object using primary key
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("delete from Photo where path=:Path");

		// set userFromId to theId
		query.setParameter("Path", path);

		// update the query
		query.executeUpdate();

	}
}
