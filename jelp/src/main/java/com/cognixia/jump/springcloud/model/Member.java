package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Role {
		ROLE_USER, ROLE_ADMIN, ADMIN, USER
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long member_id;
	
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

	// NW 2021-09-10 (Security): Added columns for login credentials
	@Column(unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(columnDefinition = "boolean default true")
	private boolean enabled;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	public Member(Long member_id, String fname, String lname, String address, String email, String city, String state,
			String country, Boolean isAdmin, String username, String password, Boolean enabled, Role role, List<Review> reviews) {
		super();
		this.member_id = member_id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.email = email;
		this.city = city;
		this.state = state;
		this.country = country;
		this.isAdmin = false;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.reviews = reviews;
	}

	public Member() {
		this(-1L, "N/A", "N/A", "N/A", "N/A", "N/A", "N/A" , "N/A", false, "N/A", "N/A", false, Role.ROLE_USER, new ArrayList<Review>());
	}

	@Transient
	private List<Review> reviews;

	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
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

	// NW 2021-09-10 (Security): Added getters and setters for the login credentials
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	// NW 2021-09-10 (Security): Re-format to be a bit more readable
	@Override
	public String toString() {
		return "{" +
			" member_id='" + getMember_id() + "'" +
			", fname='" + getFname() + "'" +
			", lname='" + getLname() + "'" +
			", address='" + getAddress() + "'" +
			", email='" + getEmail() + "'" +
			", city='" + getCity() + "'" +
			", state='" + getState() + "'" +
			", country='" + getCountry() + "'" +
			", isAdmin='" + getIsAdmin() + "'" +
			", username='" + getUsername() + "'" +
			", password='" + getPassword() + "'" +
			", reviews='" + getReviews() + "'" +
			"}";
	}

}
