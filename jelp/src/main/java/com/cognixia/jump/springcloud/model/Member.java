package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

// Table name altered to be 'Jmember' because 'Member' is a reserved word in MySQL
@Table(name = "Jmember")
@Entity
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Role {
		ROLE_USER, ROLE_ADMIN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Mbr_Id")
	private Long mbrId;
	
	@Column(name = "Mbr_Fname")
	private String fname;
	
	@Column(name = "Mbr_Lname")
	private String lname;
	
	@Column(name = "Mbr_Email")
	private String email;

	@Column(name = "Mbr_IsAdmin", columnDefinition = "boolean default false")
	private Boolean isAdmin;

	@Column(name = "Mbr_Username", unique = true, nullable = false)
	private String username;

	@Column(name = "Mbr_Password", nullable = false)
	private String password;

	@Column(columnDefinition = "boolean default true")
	private Boolean enabled;

	@Transient
	private Role role;
	
	// @ManyToOne
	// @JoinColumn(name = "City_Id", nullable = false, insertable = false, updatable = false)
	// private City city;

	@Transient
	@OneToMany(mappedBy = "member")
	private List<Review> reviews;
	
	public Member() {
		this(-1L, "N/A", "N/A", "N/A", false, "N/A", "N/A", false, new ArrayList<>());
	}

	public Member(Long mbrId, String fname, String lname, String email, Boolean isAdmin, String username, String password, boolean enabled, List<Review> reviews) {
		this.mbrId = mbrId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.isAdmin = isAdmin;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = isAdmin ? Role.ROLE_ADMIN : Role.ROLE_USER;
		this.reviews = reviews;
	}

	public Long getMbrId() {
		return this.mbrId;
	}

	public void setMbrId(Long mbrId) {
		this.mbrId = mbrId;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean isIsAdmin() {
		return this.isAdmin;
	}

	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "{" +
			" mbrId='" + getMbrId() + "'" +
			", fname='" + getFname() + "'" +
			", lname='" + getLname() + "'" +
			", email='" + getEmail() + "'" +
			", isAdmin='" + isIsAdmin() + "'" +
			", username='" + getUsername() + "'" +
			", password='" + getPassword() + "'" +
			", enabled='" + isEnabled() + "'" +
			", role='" + getRole() + "'" +
			", reviews='" + getReviews() + "'" +
			"}";
	}

}
