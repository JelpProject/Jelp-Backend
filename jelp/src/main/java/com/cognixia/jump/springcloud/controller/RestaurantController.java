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
import com.cognixia.jump.springcloud.repository.RestaurantRepository;



@RequestMapping("/api")
@RestController
public class RestaurantController {

		@Autowired
		RestaurantRepository service;
		
		
		@GetMapping("/restaurants")
		public List<Restaurant> getAllRestaurants() {
			
			return service.findAll();
		}
		
		
		@GetMapping("/restaurants/{id}")
		public Restaurant getRestaurant(@PathVariable int id) {
			
			Optional<Restaurant> restaurantOpt = service.findById(id);
			
			if(restaurantOpt.isPresent()) {
				return restaurantOpt.get();
			}
			
			return new Restaurant();
		}
		
		
		@PostMapping("/add/restaurant")
		public void addRestaurant(@RequestBody Restaurant newRestaurant) {
				
			Restaurant added = service.save(newRestaurant); // save() does an insert or update (depends on id passed)
			
			System.out.println("Added: " + added);
			
		}
		
		
		@PutMapping("/update/restaurant")
		public @ResponseBody String updateRestaurant(@RequestBody Restaurant updateRestaurant) {
			
			// check if restaurant exists, then update them
			
			Optional<Restaurant> found = service.findById(updateRestaurant.getRestaurant_id());
			
			if(found.isPresent()) {
				service.save(updateRestaurant);
				return "Saved: " + updateRestaurant.toString();
			}
			else {
				return "Could not update restaurant, the id = " + updateRestaurant.getRestaurant_id() + " doesn't exist";
			}
			
		}
		
		
		
		@DeleteMapping("/delete/restaurant/{id}")
		public ResponseEntity<String> deleteRestaurant(@PathVariable int id) {
			
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
