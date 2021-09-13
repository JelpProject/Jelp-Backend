package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Rest_Id")
	private Long restaurantId;
	
	@Column(name = "Rest_Name")
	private String name;
	
	@Column(name = "Rest_Address", columnDefinition = "varchar(60)")
	private String address;
	
	@Size(min = 10, max = 10)
	@Column(name = "Rest_Phone", columnDefinition = "char(10)")
	private String phone;

	@Column(name = "Rest_Desc", columnDefinition = "text(500)")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "City_Id", nullable = false, insertable = false, updatable = false)
	private City city;
	
	@Transient
	@OneToMany(mappedBy = "restaurant")
	private List<Review> reviews;

	public Restaurant() {
		this(-1L, "N/A", "N/A", "N/A", "N/A", new City(), new ArrayList<>());
	}

	public Restaurant(Long restaurant_id, String name, String address, String phone, String description, City city, List<Review> reviews) {
		this.restaurantId = restaurant_id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.description = description;
		this.city = city;
		this.reviews = reviews;
	}

	public Long getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
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
			" restaurantId='" + getRestaurantId() + "'" +
			", name='" + getName() + "'" +
			", address='" + getAddress() + "'" +
			", phone='" + getPhone() + "'" +
			", description='" + getDescription() + "'" +
			", city='" + getCity() + "'" +
			", reviews='" + getReviews() + "'" +
			"}";
	}
}
