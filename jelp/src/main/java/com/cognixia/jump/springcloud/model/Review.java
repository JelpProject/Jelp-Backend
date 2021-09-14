package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
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
	private Integer rating;

	@Column(name = "Rvw_Headline", columnDefinition = "varchar(50)")
	private String headline;
	
	@Column(name = "Rvw_Detail", columnDefinition = "text(500)")
	private String detail;

	@Column(name = "Rvw_Timestamp", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private LocalDateTime timeStamp;
	
	@ManyToMany
	@Column(name = "Mbr_Id")
	private Long mbrId;
	
	@ManyToMany
	@Column(name = "Rest_Id")
	private Long restaurantId;

	@Transient
	private MemberDto member;

	@Transient
	private RestaurantDto restaurant;

	public Review() {
		this(-1L, 0, "N/A", "N/A", LocalDateTime.now(), -1L, -1L, null, null);
	}

	public Review(Long rvwId, int rating, String headline, String detail, LocalDateTime timeStamp, Long mbrId, Long restaurantId, MemberDto member, RestaurantDto restaurant) {
		this.rvwId = rvwId;
		this.rating = rating;
		this.headline = headline;
		this.detail = detail;
		this.timeStamp = timeStamp;
		this.mbrId = mbrId;
		this.restaurantId = restaurantId;
		this.member = member;
		this.restaurant = restaurant;
	}

	public Long getRvwId() {
		return this.rvwId;
	}

	public void setRvwId(Long rvwId) {
		this.rvwId = rvwId;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
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

	public Long getMbrId() {
		return this.mbrId;
	}

	public void setMbrId(Long mbrId) {
		this.mbrId = mbrId;
	}

	public Long getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public MemberDto getMember() {
		return this.member;
	}

	public void setMember(MemberDto member) {
		this.member = member;
	}

	public RestaurantDto getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(RestaurantDto restaurant) {
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
			", mbrId='" + getMbrId() + "'" +
			", restaurantId='" + getRestaurantId() + "'" +
			", member='" + getMember() + "'" +
			", restaurant='" + getRestaurant() + "'" +
			"}";
	}

}
