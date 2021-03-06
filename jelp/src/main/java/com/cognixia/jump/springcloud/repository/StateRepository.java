package com.cognixia.jump.springcloud.repository;

import com.cognixia.jump.springcloud.model.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{
    State findByName(String name);
}
