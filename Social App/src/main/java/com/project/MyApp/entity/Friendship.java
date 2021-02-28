package com.project.MyApp.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.MyApp.Enums.FriendshipStatusEnum;

@Entity
@Table(name = "friendships")
public class Friendship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5726085617079847958L;

	@Id
	@ManyToOne
	@JoinColumn(name = "requester_id")
	User requester;

	@Id
	@ManyToOne
	@JoinColumn(name = "addressee_id")
	User addressee;

	@Override
	public String toString() {
		return "Friendship [requester=" + requester.getUsername() + ", addressee=" + addressee.getUsername() + ", status=" + status
				+ ", registrationDate=" + registrationDate + "]";
	}

	@Column(name = "Status")
	FriendshipStatusEnum status;

	public Friendship() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "created_date_time", columnDefinition = "TIMESTAMP")
	private Date registrationDate;

	public Friendship(User requester, User addressee, FriendshipStatusEnum status) {
		super();
		this.requester = requester;
		this.addressee = addressee;
		this.status = status;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public FriendshipStatusEnum getStatus() {
		return status;
	}

	public void setStatus(FriendshipStatusEnum status) {
		this.status = status;
	}

	public User getAddressee() {
		return addressee;
	}

	public void setAddressee(User andressee) {
		this.addressee = andressee;
	}
	
	
}
