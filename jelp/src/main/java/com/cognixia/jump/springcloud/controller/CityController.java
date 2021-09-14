package com.cognixia.jump.springcloud.controller;

import java.util.List;
import java.util.Optional;

import com.cognixia.jump.springcloud.model.City;
import com.cognixia.jump.springcloud.model.State;
import com.cognixia.jump.springcloud.repository.CityRepository;
import com.cognixia.jump.springcloud.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CityController {
    
    @Autowired
    CityRepository cityRepo;

    @Autowired
    StateRepository stateRepo;

    @GetMapping("/cities")
    public Iterable<City> getAllCities() {
        List<City> cities = cityRepo.findAll();

        for (City city : cities) {
            city.setCityState(stateRepo.getById(city.getStateId()));
        }

        return cities;
        
    }

    @GetMapping("/city/{id}")
    public City getCityById(@PathVariable Long id) {
        Optional<City> found = cityRepo.findById(id);

        if (found.isPresent()) {
            City city = found.get();
            city.setCityState(stateRepo.getById(city.getStateId()));
            return city;
        }

        return null;
    }

    @PostMapping("/add/city")
    public void addCity(@RequestBody City newCity) {
        newCity.setCityId(-1L);

        // Grab the state
        State cityState = stateRepo.findByName(newCity.getCityState().getName());

        // if state exists
        if (cityState != null) {
            newCity.setStateId(cityState.getStateId());
        }
        // state doesn't exist
        else {
            // create a new state
            stateRepo.save(new State(-1L, newCity.getCityState().getName()));
        }

        City added = cityRepo.save(newCity);

        System.out.println("Added: " + added);
    }

    @PutMapping("/update/city")
    public String updateCity(@RequestBody City updateCity) {
        Optional<City> found = cityRepo.findById(updateCity.getCityId());

        if (found.isPresent()) {
            cityRepo.save(updateCity);
            return "Saved: " + updateCity.toString();
        }
        else {
            return "Could not update city, the id = " + updateCity.getCityId() + " doesn't exist";
        }
    }

    @DeleteMapping("/delete/city/{id}")
    public ResponseEntity<String> deleteState(@PathVariable Long id) {
        Optional<City> found = cityRepo.findById(id);

        if (found.isPresent()) {
            cityRepo.deleteById(id);
            return ResponseEntity.status(200).body("Deleted city with id = " + id);
        }
        else {
            return ResponseEntity.status(400).body("City with id = " + id + " not found");
        }
    }

}
