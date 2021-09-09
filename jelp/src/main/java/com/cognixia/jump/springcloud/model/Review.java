package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int review_id;
	
	@Column 
	private int rating;
	
	@Column
	private String comment;
	
	@Column
	private int member_id;
	
	@Column
	private int restaurant_id;
	
	@Column
	private LocalDateTime timeStamp;
	
	

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int review_id, int rating, String comment, int member_id, int restaurant_id,
			LocalDateTime timeStamp) {
		super();
		this.review_id = review_id;
		this.rating = rating;
		this.comment = comment;
		this.member_id = member_id;
		this.restaurant_id = restaurant_id;
		this.timeStamp = LocalDateTime.now();
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Review [review_id=" + review_id + ", rating=" + rating + ", comment=" + comment + ", member_id="
				+ member_id + ", restaurant_id=" + restaurant_id + ", timeStamp=" + timeStamp + "]";
	}
	
	
	

}
