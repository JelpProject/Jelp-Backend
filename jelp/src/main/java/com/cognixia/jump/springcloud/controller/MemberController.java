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

import com.cognixia.jump.springcloud.model.Member;
import com.cognixia.jump.springcloud.model.MemberProfileDto;
import com.cognixia.jump.springcloud.repository.MemberRepository;
import com.cognixia.jump.springcloud.repository.ReviewRepository;
import com.cognixia.jump.springcloud.service.ReviewService;

@RestController
@RequestMapping("/api")
public class MemberController {
	@Autowired
	MemberRepository service;
	
	@Autowired
	ReviewRepository rvwRepo;
	
	@GetMapping("/members")
	public Iterable<Member> getAllMembers() {
		
		// find all registered members
		List<Member> memberWRevs = service.findAll();

		// For each member
		for (int i = 0; i< memberWRevs.size(); i++) {
			Member member = memberWRevs.get(i);

			// Retrieve all the reviews the member made
			member.setReviews(rvwRepo.findAllBymbrId(member.getMbrId()));

			// Fill in the review metadata
			ReviewService.populateReviewMetadata(member.getReviews());
		}

		return memberWRevs;
		
	}
	
	// For grabbing current user data
	@GetMapping("/members/{username}")
	public Member getMember(@PathVariable String username) {
		
		Optional<Member> memberOpt = service.findByUsername(username);
		
		if(memberOpt.isPresent()) {
			Member member = memberOpt.get();

			// retrive reviews made by user
			member.setReviews(rvwRepo.findAllBymbrId(memberOpt.get().getMbrId()));

			// grab review metadata
			ReviewService.populateReviewMetadata(member.getReviews());
			
			return memberOpt.get();
		}
		
		return null;
	}

	// grabbing another user's data
	@GetMapping("/member/profile/{username}")
	public MemberProfileDto getMemberProfile(@PathVariable String username) {
		Optional<MemberProfileDto> found = service.findByUsername(username);

		if (found.isPresent()) {
			MemberProfileDto member = found.get();

			// grab reviews made by the member
			member.setReviews(rvwRepo.findAllBymbrId(member.getMbrId()));

			// populate review metadata
			ReviewService.populateReviewMetadata(member.getReviews());

			return member;

		}

		return null;
	}
	
	@PostMapping("/add/member")
	public void addMember(@RequestBody Member newMember) {
			
		Member added = service.save(newMember); // save() does an insert or update (depends on id passed)
		
		System.out.println("Added: " + added);
		
	}
	
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
