package com.cognixia.jump.springcloud.repository;

import java.util.List;

import com.cognixia.jump.springcloud.model.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
    List<City> findAll();
    List<City> findAllByStateId(Long stateId);

    City findByNameAndStateId(String name, Long stateId);
}
