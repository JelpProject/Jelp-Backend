package com.cognixia.jump.springcloud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	List<Member> findAll();

	// NW 2021-09-10 (Security): Find by member's username
	Optional<Member> findByUsername(String username);
}
