package com.cognixia.jump.springcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	List<Member> findAll();
}
