package com.cognixia.jump.springcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	List<Restaurant> findAll();

	List<Restaurant>  findAllByNameContaining(String name);
	
}
