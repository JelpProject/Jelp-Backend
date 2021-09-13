package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Rvw_Id")
	private Long rvwId;
	
	@Min(value = 0)
	@Max(value = 5)
	@Column(name = "Rvw_Rating")
	private int rating;

	@Column(name = "Rvw_Headline", columnDefinition = "varchar(50)")
	private String headline;
	
	@Column(name = "Rvw_Detail", columnDefinition = "text(500)")
	private String detail;

	@Column(name = "Rvw_Timestamp", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private LocalDateTime timeStamp;
	
	@ManyToOne
	@JoinColumn(name = "Mbr_Id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "Rest_Id")
	private Restaurant restaurant;

	public Review() {
		this(-1L, 0, "N/A", "N/A", LocalDateTime.now(), new Member(), new Restaurant());
	}

	public Review(Long rvwId, int rating, String headline, String detail, LocalDateTime timeStamp, Member member, Restaurant restaurant) {
		this.rvwId = rvwId;
		this.rating = rating;
		this.headline = headline;
		this.detail = detail;
		this.timeStamp = timeStamp;
		this.member = member;
		this.restaurant = restaurant;
	}

	public Long getRvwId() {
		return this.rvwId;
	}

	public void setRvwId(Long rvwId) {
		this.rvwId = rvwId;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "{" +
			" rvwId='" + getRvwId() + "'" +
			", rating='" + getRating() + "'" +
			", headline='" + getHeadline() + "'" +
			", detail='" + getDetail() + "'" +
			", timeStamp='" + getTimeStamp() + "'" +
			", member='" + getMember() + "'" +
			", restaurant='" + getRestaurant() + "'" +
			"}";
	}

}
