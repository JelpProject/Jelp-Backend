package com.cognixia.jump.springcloud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.springcloud.model.Restaurant;
import com.cognixia.jump.springcloud.model.RestaurantDto;
import com.cognixia.jump.springcloud.repository.MemberRepository;
import com.cognixia.jump.springcloud.repository.RestaurantRepository;
import com.cognixia.jump.springcloud.repository.ReviewRepository;
import com.cognixia.jump.springcloud.service.ReviewService;



@RequestMapping("/api")
@RestController
public class RestaurantController {

	@Autowired
	RestaurantRepository service;
	
	@Autowired
	ReviewRepository rvwRepo;

	@Autowired
	MemberRepository mbrRepo;
	
	@GetMapping("/restaurants")
	public Iterable<Restaurant> getAllRestaurants() {
		
		List<Restaurant> restaurantsWRevs = service.findAll();

		//  for (int i = 0; i< restaurantsWRevs.size(); i++) {
		//  	restaurantsWRevs.get(i).setReviews(rvwRepo.findAllByrestaurantId(restaurantsWRevs.get(i).getRestaurantId()));
		//  }

		// for each restaurant
		for (Restaurant restaurant : restaurantsWRevs) {

			// retrieve all of their reviews
			restaurant.setReviews(rvwRepo.findAllByrestaurantId(restaurant.getRestaurantId()));

			// populate the restaurant metadata
			ReviewService.populateReviewMetadata(restaurant.getReviews());
		}

		return restaurantsWRevs;
		
	}
	
	@GetMapping("/restaurants/name/{name}")
	public Iterable<RestaurantDto> getAllRestaurantsLike(@PathVariable String name) {
		
		// Change type to RestaurantDto because we don't need all the restaurant information
		List<RestaurantDto> restaurants = service.findAllByNameContaining(name);
		
		// for (int i = 0; i< restaurantsWRevs.size(); i++) {
		// restaurantsWRevs.get(i).setReviews(rvwRepo.findAllByrestaurantId(restaurantsWRevs.get(i).getRestaurantId()));
		// }

		return restaurants;
		
	}
	
	
	@GetMapping("/restaurants/{id}")
	public Restaurant getRestaurant(@PathVariable Long id) {
		
		// try to find the restaurant
		Optional<Restaurant> restaurantOpt = service.findById(id);
		
		// if found
		if(restaurantOpt.isPresent()) {
			// retrieve the restaurant object
			Restaurant restaurant = restaurantOpt.get();

			// grab all the restaurant's reviews
			restaurant.setReviews(rvwRepo.findAllByrestaurantId(restaurant.getRestaurantId()));

			// populate review metadata
			ReviewService.populateReviewMetadata(restaurant.getReviews());

			return restaurantOpt.get();
		}
		
		return null;
	}
	
	
	@PostMapping("/add/restaurant")
	public void addRestaurant(@RequestBody Restaurant newRestaurant) {
			
		Restaurant added = service.save(newRestaurant); // save() does an insert or update (depends on id passed)
		rvwRepo.saveAll(added.getReviews());
		System.out.println("Added: " + added);
		
	}
	
	
	@PutMapping("/update/restaurant")
	public @ResponseBody String updateRestaurant(@RequestBody Restaurant updateRestaurant) {
		
		// check if restaurant exists, then update them
		
		Optional<Restaurant> found = service.findById(updateRestaurant.getRestaurantId());
		
		if(found.isPresent()) {
			service.save(updateRestaurant);
			rvwRepo.saveAll(found.get().getReviews());
			return "Saved: " + updateRestaurant.toString();
		}
		else {
			return "Could not update restaurant, the id = " + updateRestaurant.getRestaurantId() + " doesn't exist";
		}
		
	}
	
	
	
	@DeleteMapping("/delete/restaurant/{id}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
		
		Optional<Restaurant> found = service.findById(id);
		
		if(found.isPresent()) {
			
			service.deleteById(id);
			
			return ResponseEntity.status(200).body("Deleted restaurant with id = " + id);	
		}
		else {
			return ResponseEntity.status(400)
					.header("restaurant id", id + "")
					.body("Restaurant with id = " + id + " not found");
		}
			
	}	

}
