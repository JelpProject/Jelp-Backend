package com.cognixia.jump.springcloud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.Jmember;

@Repository
public interface MemberRepository extends JpaRepository<Jmember, Integer>{
	List<Jmember> findAll();

	// NW 2021-09-10 (Security): Find by member's username
	Optional<Jmember> findByUsername(String username);
}
