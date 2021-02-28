package com.project.MyApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_images")
public class UserImages {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	@Column(name = "profile_photo")
	private String profilePhoto;

	@OneToMany(mappedBy = "userImages", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Photo> photos;

	@Column(name = "count")
	private int imageCount;

	@Override
	public String toString() {
		return "UserImages [id=" + id + ", profilePhoto=" + profilePhoto + ", photos=" + photos + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void addPhotos(List<Photo> newPhotos) {

		if (this.photos == null) {
			this.photos = new ArrayList<Photo>();
		}

		this.photos.addAll(newPhotos);

	}

	public void deletePhoto(String path) {

		for (int i = 0; i < this.getPhotos().size(); i++) {

			if (this.getPhotos().get(i).getPath().equals(path)) {
				System.out.println("??????" + this.getPhotos().get(i).getPath());
				this.getPhotos().remove(i);
				break;
			}
		}
	}

}
