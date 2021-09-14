package com.cognixia.jump.springcloud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.springcloud.model.Member;
import com.cognixia.jump.springcloud.model.MemberProfileDto;
import com.cognixia.jump.springcloud.model.Review;
import com.cognixia.jump.springcloud.repository.MemberRepository;
import com.cognixia.jump.springcloud.repository.RestaurantRepository;
import com.cognixia.jump.springcloud.repository.ReviewRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MemberController {
	@Autowired
	MemberRepository service;
	
	@Autowired
	ReviewRepository rvwRepo;

	@Autowired
	RestaurantRepository restRepo;
	
	@CrossOrigin
	@GetMapping("/members")
	public Iterable<Member> getAllMembers() {
		
		// find all registered members
		List<Member> memberWRevs = service.findAll();

		// For each member
		for (int i = 0; i< memberWRevs.size(); i++) {
			Member member = memberWRevs.get(i);

			// Retrieve all the reviews the member made
			member.setReviews(rvwRepo.findAllBymbrId(member.getMbrId()));

			// for each review
			for (Review review : member.getReviews()) {

				// retrieve the member who made the review
				review.setMember(service.findByMbrId(review.getMbrId()));

				// grab the restaurant that the review was made for
				review.setRestaurant(restRepo.findByRestaurantId(review.getRestaurantId()));
			}
		}

		return memberWRevs;
		
	}
	
	// For grabbing current user data
	@CrossOrigin
	@GetMapping("/member/{username}")
	public Member getMember(@PathVariable String username) {
		
		Member member = service.findByUsername(username, Member.class);
		
		if(member != null) {
			// Member member = memberOpt.get();

			// retrive reviews made by user
			member.setReviews(rvwRepo.findAllBymbrId(member.getMbrId()));

			// for each review
			for (Review review : member.getReviews()) {

				// retrieve the member who made the review
				review.setMember(service.findByMbrId(review.getMbrId()));

				// grab the restaurant that the review was made for
				review.setRestaurant(restRepo.findByRestaurantId(review.getRestaurantId()));
			}
			
			return member;
		}
		
		return null;
	}

	// grabbing another user's data
	// 2021-09-14: Currently not working
	@CrossOrigin
	@GetMapping("/member/profile/{username}")
	public MemberProfileDto getMemberProfile(@PathVariable String username) {
		MemberProfileDto member = service.findByUsername(username, MemberProfileDto.class);

		if (member != null) {
			// MemberProfileDto member = found.get();

			System.out.println(member);

			// grab reviews made by the member
			// member.setReviews(rvwRepo.findAllBymbrId(member.getMbrId()));

			// // for each review
			// for (Review review : member.getReviews()) {

			// 	// retrieve the member who made the review
			// 	// review.setMember(service.findByMbrId(review.getMbrId()));

			// 	// grab the restaurant that the review was made for
			// 	review.setRestaurant(restRepo.findByRestaurantId(review.getRestaurantId()));
			// }

			return member;

		}

		return null;
	}
	
	@CrossOrigin
	@PostMapping("/add/member")
	public ResponseEntity<?> addMember(@RequestBody Member newMember) {
		
		// Check if member already exists
		Optional<Member> memberByEmail = service.findByEmail(newMember.getEmail());
		Member memberByUsername = service.findByUsername(newMember.getUsername(), Member.class);

		// if the email or username has already been taken
		if (memberByEmail.isPresent() || memberByUsername != null) {
			return ResponseEntity.status(400).body("User already exists");
		}

		newMember.setMbrId(-1L);
		newMember.setEnabled(true);

		Member added = service.save(newMember); // save() does an insert or update (depends on id passed)
		
		return ResponseEntity.status(200).body("Added: " + added);
		
	}
	
	@CrossOrigin
	@PutMapping("/update/member")
	public @ResponseBody String updateMember(@RequestBody Member updateMember) {
		
		// check if member exists, then update them
		
		Optional<Member> found = service.findById(updateMember.getMbrId());
		
		if(found.isPresent()) {
			service.save(updateMember);
			return "Saved: " + updateMember.toString();
		}
		else {
			return "Could not update member, the id = " + updateMember.getMbrId() + " doesn't exist";
		}
		
	}
	
	@DeleteMapping("/delete/member/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable Long id) {
		
		Optional<Member> found = service.findById(id);
		
		if(found.isPresent()) {
			
			service.deleteById(id);
			
			return ResponseEntity.status(200).body("Deleted member with id = " + id);	
		}
		else {
			return ResponseEntity.status(400)
					.header("member id", id + "")
					.body("Member with id = " + id + " not found");
		}
			
	}
	

}
