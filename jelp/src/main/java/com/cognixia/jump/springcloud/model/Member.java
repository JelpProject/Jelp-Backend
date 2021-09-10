package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memberId;
	
	@Column
	private String fname;
	
	@Column
	private String lname;
	
	@Column
	private String address;
	
	@Column
	private String email;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private String country;
	
	@Column
	private Boolean isAdmin;
	
	
	
	public Member(int memberId, String fname, String lname, String address, String email, String city, String state,
			String country, Boolean isAdmin, List<Review> reviews) {
		super();
		this.memberId = memberId;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.email = email;
		this.city = city;
		this.state = state;
		this.country = country;
		this.isAdmin = false;
		this.reviews = reviews;
	}
	
	

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Transient
	private List<Review> reviews;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}



	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", fname=" + fname + ", lname=" + lname + ", address=" + address
				+ ", email=" + email + ", city=" + city + ", state=" + state + ", country=" + country + ", isAdmin="
				+ isAdmin + ", reviews=" + reviews + "]";
	}
	
	
	
	
	
}
