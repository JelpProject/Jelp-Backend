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
	private int reviewId;
	
	@Column 
	private int rating;
	
	@Column
	private String comment;
	
	@Column
	private int memberId;
	
	@Column
	private int restaurantId;
	
	@Column
	private LocalDateTime timeStamp;
	
	

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int reviewId, int rating, String comment, int memberId, int restaurantId,
			LocalDateTime timeStamp) {
		super();
		this.reviewId = reviewId;
		this.rating = rating;
		this.comment = comment;
		this.memberId = memberId;
		this.restaurantId = restaurantId;
		this.timeStamp = LocalDateTime.now();
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", rating=" + rating + ", comment=" + comment + ", memberId="
				+ memberId + ", restaurantId=" + restaurantId + ", timeStamp=" + timeStamp + "]";
	}
	
	
	

}
