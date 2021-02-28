package com.project.MyApp.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "registration_date", columnDefinition = "TIMESTAMP")
	private Date registrationDate;

	@Column(name = "first_name")
	private String firstName;

	public UserImages getImages() {
		return images;
	}

	public void setImages(UserImages images) {
		this.images = images;
	}

	@Column(name = "email")
	private String email;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "authority")
	private String authority;

	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "country")
	private String country;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "city")
	private String city;
	
	@Column(name = "occupation")
	private String occupation;


	@OneToOne(targetEntity = UserImages.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "image_db_id")
	private UserImages images;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public User(String firstName, String email, String lastName, String username, String password) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", registrationDate=" + registrationDate + ", firstname=" + firstName + ", lastName="
				+ lastName + ", username=" + username + ", password=" + password + "]";
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enableda) {
		this.enabled = enableda;
	}
	

	public String getFullName() {
		
		return this.firstName + " " + this.lastName;
	}
	
	public String getLocation()
	{
		return this.getCity() + ", " + getCountry();
	}

}
