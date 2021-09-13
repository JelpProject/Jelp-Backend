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

import com.cognixia.jump.springcloud.model.Jmember;
import com.cognixia.jump.springcloud.repository.MemberRepository;
import com.cognixia.jump.springcloud.repository.ReviewRepository;

@RestController
@RequestMapping("/api")
public class MemberController {
	@Autowired
	MemberRepository service;
	
	@Autowired
	ReviewRepository repo;
	
	@GetMapping("/members")
	public Iterable<Jmember> getAllMembers() {
		
		List<Jmember> memberWRevs = service.findAll();
		for (int i = 0; i< memberWRevs.size(); i++) {
			memberWRevs.get(i).setReviews(repo.findAllBymemberId(memberWRevs.get(i).getMemberId()));
		}
		return memberWRevs;
		
	}
	
	@GetMapping("/members/{id}")
	public Jmember getMember(@PathVariable int id) {
		
		Optional<Jmember> memberOpt = service.findById(id);
		
		if(memberOpt.isPresent()) {
			memberOpt.get().setReviews(repo.findAllBymemberId(memberOpt.get().getMemberId()));
			return memberOpt.get();
		}
		
		return new Jmember();
	}
	
	@PostMapping("/add/member")
	public void addMember(@RequestBody Jmember newMember) {
			
		Jmember added = service.save(newMember); // save() does an insert or update (depends on id passed)
		
		System.out.println("Added: " + added);
		
	}
	
	@PutMapping("/update/member")
	public @ResponseBody String updateMember(@RequestBody Jmember updateMember) {
		
		// check if member exists, then update them
		
		Optional<Jmember> found = service.findById(updateMember.getMemberId());
		
		if(found.isPresent()) {
			service.save(updateMember);
			return "Saved: " + updateMember.toString();
		}
		else {
			return "Could not update member, the id = " + updateMember.getMemberId() + " doesn't exist";
		}
		
	}
	
	
	@DeleteMapping("/delete/member/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable int id) {
		
		Optional<Jmember> found = service.findById(id);
		
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
