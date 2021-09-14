package com.cognixia.jump.springcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.Member;
import com.cognixia.jump.springcloud.model.MemberDto;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	List<Member> findAll();

	// Find member's username for spring security use
	<T> T findByUsername(String username, Class<T> type);

	// Projection of user
	// Can find a user using MemberDto and MemberProfileDto to grab only certain
	// required information from the member
	MemberDto findByMbrId(Long mbrId);
}
