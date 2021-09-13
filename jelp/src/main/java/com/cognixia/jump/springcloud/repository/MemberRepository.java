package com.cognixia.jump.springcloud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	List<Member> findAll();

	// Find member's username for spring security use
	Optional<Member> findByUsername(String username);
}
