package com.cognixia.jump.springcloud.controller;

import java.util.List;
import java.util.Optional;

import com.cognixia.jump.springcloud.model.City;
import com.cognixia.jump.springcloud.model.State;
import com.cognixia.jump.springcloud.repository.CityRepository;
import com.cognixia.jump.springcloud.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CityController {
    
    @Autowired
    CityRepository cityRepo;

    @Autowired
    StateRepository stateRepo;

    @CrossOrigin
    @GetMapping("/cities")
    public Iterable<City> getAllCities() {
        List<City> cities = cityRepo.findAll();

        for (City city : cities) {

            city.setCityState(stateRepo.getById(city.getStateId()));
            
        }

        // for (int i = 0; i < cities.size(); i ++) {

        //     System.out.println(stateRepo.findById(cities.get(i).getStateId()).get().getClass().getName());

        //     cities.get(i).setCityState(stateRepo.findById(cities.get(i).getStateId()).get());
        // }

        return cities;
        
    }

    @CrossOrigin
    @GetMapping("/city/{id}")
    public City getCityById(@PathVariable Long id) {
        Optional<City> found = cityRepo.findById(id);

        if (found.isPresent()) {
            City city = found.get();
            city.setCityState(stateRepo.findById(city.getStateId()).get());
            return city;
        }

        return null;
    }

    @CrossOrigin
    @PostMapping("/add/city")
    public ResponseEntity<String> addCity(@RequestBody City newCity) {
        City added = null;
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
            cityState = stateRepo.save(new State(-1L, newCity.getCityState().getName()));

            // Set the city's state to the new state
            newCity.setStateId(cityState.getStateId());
            newCity.setCityState(cityState);
        }

        // Check if the city already exists before adding it
        if (cityRepo.findByNameAndStateId(newCity.getName(), newCity.getStateId()) == null) {
            added = cityRepo.save(newCity);

        // Record for city already exists
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("City already exists");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Added: " + added);
    }

    @CrossOrigin
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
