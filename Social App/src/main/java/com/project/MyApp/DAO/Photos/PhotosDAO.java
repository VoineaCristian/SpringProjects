package com.project.MyApp.DAO.Photos;

import java.util.List;

public interface PhotosDAO {

	void addPhotos(String username, List<String> paths);

	void setProfilePhoto(String username, String path);



	void deletePhoto(String path);


}
