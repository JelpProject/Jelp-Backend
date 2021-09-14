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

import com.cognixia.jump.springcloud.model.Review;
import com.cognixia.jump.springcloud.repository.MemberRepository;
import com.cognixia.jump.springcloud.repository.RestaurantRepository;
import com.cognixia.jump.springcloud.repository.ReviewRepository;


@RequestMapping("/api")
@RestController
public class ReviewController {
	
	@Autowired
	ReviewRepository service;

	@Autowired
	MemberRepository mbrRepo;

	@Autowired
	RestaurantRepository restRepo;
	
	
	@GetMapping("/reviews")
	public List<Review> getAllReviews() {

		List<Review> reviews = service.findAll();

		for (Review review : reviews) {
			review.setMember(mbrRepo.findByMbrId(review.getMbrId()));
			review.setRestaurant(restRepo.findByRestaurantId(review.getRestaurantId()));
		}
		
		return reviews;
	}
	
	
	@GetMapping("/reviews/{id}")
	public Review getReview(@PathVariable Long id) {
		
		Optional<Review> reviewOpt = service.findById(id);
		
		if(reviewOpt.isPresent()) {

			Review review = reviewOpt.get();

			review.setMember(mbrRepo.findByMbrId(review.getMbrId()));
			review.setRestaurant(restRepo.findByRestaurantId(review.getRestaurantId()));

			return review;
		}
		
		return new Review();
	}
	
	
	@PostMapping("/add/review")
	public void addReview(@RequestBody Review newReview) {
			
		Review added = service.save(newReview); // save() does an insert or update (depends on id passed)
		
		System.out.println("Added: " + added);
		
	}
	
	
	@PutMapping("/update/review")
	public @ResponseBody String updateReview(@RequestBody Review updateReview) {
		
		// check if review exists, then update them
		
		Optional<Review> found = service.findById(updateReview.getRvwId());
		
		if(found.isPresent()) {
			service.save(updateReview);
			return "Saved: " + updateReview.toString();
		}
		else {
			return "Could not update review, the id = " + updateReview.getRvwId() + " doesn't exist";
		}
		
	}
	
	
	
	@DeleteMapping("/delete/review/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long id) {
		
		Optional<Review> found = service.findById(id);
		
		if(found.isPresent()) {
			
			service.deleteById(id);
			
			return ResponseEntity.status(200).body("Deleted review with id = " + id);	
		}
		else {
			return ResponseEntity.status(400)
					.header("review id", id + "")
					.body("Review with id = " + id + " not found");
		}
			
	}
	

	
}
