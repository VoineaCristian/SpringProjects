package com.project.MyApp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="photos")
public class Photo{
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;
	
	@Column(name="path")
	private String path;
	

	@ManyToOne
    @JoinColumn(name="user_images_id",nullable=false)
    private UserImages userImages;
	
	public Photo(String path, UserImages userImages) {
		super();
		this.path = path;
		this.userImages = userImages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public UserImages getUserImages() {
		return userImages;
	}

	public void setUserImages(UserImages userImages) {
		this.userImages = userImages;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Photos [id=" + id + ", photo=" + path + ", userImages=" + "]";
	}


}
	

