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
import com.cognixia.jump.springcloud.repository.MemberRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MemberController {
	@Autowired
	MemberRepository service;
	
	@CrossOrigin
	@GetMapping("/members")
	public List<Member> getAllMembers() {
		
		return service.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/members/{id}")
	public Member getMember(@PathVariable long id) {
		
		Optional<Member> memberOpt = service.findById(id);
		
		if(memberOpt.isPresent()) {
			return memberOpt.get();
		}
		
		return new Member();
	}
	
	@CrossOrigin
	@PostMapping("/add/member")
	public void addMember(@RequestBody Member newMember) {
			
		Member added = service.save(newMember); // save() does an insert or update (depends on id passed)
		
		System.out.println("Added: " + added);
		
	}
	
	@CrossOrigin
	@PutMapping("/update/member")
	public @ResponseBody String updateMember(@RequestBody Member updateMember) {
		
		// check if member exists, then update them
		
		Optional<Member> found = service.findById(updateMember.getMember_id());
		
		if(found.isPresent()) {
			service.save(updateMember);
			return "Saved: " + updateMember.toString();
		}
		else {
			return "Could not update member, the id = " + updateMember.getMember_id() + " doesn't exist";
		}
		
	}
	
	
	@CrossOrigin
	@DeleteMapping("/delete/member/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable long id) {
		
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
