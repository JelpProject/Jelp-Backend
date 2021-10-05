package com.cognixia.jump.springcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.Restaurant;
import com.cognixia.jump.springcloud.model.RestaurantDto;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	List<Restaurant> findAll();

	List<RestaurantDto>  findAllByNameContaining(String name);

	RestaurantDto findByRestaurantId(Long restaurantId);
	
}
