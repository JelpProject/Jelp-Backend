package com.cognixia.jump.springcloud.repository;

import java.util.Optional;

import com.cognixia.jump.springcloud.model.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{
    State findByName(String name);
    Optional<State> findById(Long id);
}
