package com.cognixia.jump.springcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	List<Member> findAll();

	// Find member's username for spring security use
	<T> T findByUsername(String username);

	// Projection of user
	// Can find a user using MemberDto and MemberProfileDto to grab only certain
	// required information from the member
	<T> T findByMbrId(Long mbrId);
}
