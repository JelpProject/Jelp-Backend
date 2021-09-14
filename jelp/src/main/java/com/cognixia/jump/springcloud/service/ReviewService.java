package com.cognixia.jump.springcloud.service;

import java.util.List;

import com.cognixia.jump.springcloud.model.Review;
import com.cognixia.jump.springcloud.repository.MemberRepository;
import com.cognixia.jump.springcloud.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ReviewService {

    @Autowired
    static MemberRepository mbrRepo;

    @Autowired
    static RestaurantRepository restRepo;
    
    public static void populateReviewMetadata(List<Review> reviewList) {
		// for each review
		for (Review review : reviewList) {

			// retrieve the member who made the review
			review.setMember(mbrRepo.findByMbrId(review.getMbrId()));

			// grab the restaurant that the review was made for
			review.setRestaurant(restRepo.findByRestaurantId(review.getRestaurantId()));
		}
	}

}
