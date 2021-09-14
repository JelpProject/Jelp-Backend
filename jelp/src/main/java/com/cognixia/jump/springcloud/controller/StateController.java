package com.cognixia.jump.springcloud.controller;

import java.util.List;
import java.util.Optional;

import com.cognixia.jump.springcloud.model.State;
import com.cognixia.jump.springcloud.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
public class StateController {
    
    @Autowired
    StateRepository stateRepo;

    @CrossOrigin
    @GetMapping("/states")
    public Iterable<State> getAllStates() {
        List<State> states = stateRepo.findAll();

        return states;
    }

    @CrossOrigin
    @GetMapping("/states/{id}")
    public State getStateById(@PathVariable Long id) {
        Optional<State> state = stateRepo.findById(id);

        if (state.isPresent()) {
            return state.get();
        }

        return null;
    }

    @CrossOrigin
    @PostMapping("/add/state")
    public void addState(@RequestBody State newState) {
        newState.setStateId(-1L);
        State added = stateRepo.save(newState);

        System.out.println("Added: " + added);
    }

    @CrossOrigin
    @PutMapping("/update/state")
    public String updateState(@RequestBody State updateState) {
        Optional<State> found = stateRepo.findById(updateState.getStateId());

        if (found.isPresent()) {
            stateRepo.save(updateState);
            return "Saved: " + updateState.toString();
        }
        else {
            return "Could not update state, the id = " + updateState.getStateId() + " doesn't exist";
        }
    }

    @DeleteMapping("/delete/state/{id}")
    public ResponseEntity<String> deleteState(@PathVariable Long id) {
        Optional<State> found = stateRepo.findById(id);

        if (found.isPresent()) {
            stateRepo.deleteById(id);
            return ResponseEntity.status(200).body("Deleted member with id = " + id);
        }
        else {
            return ResponseEntity.status(400).body("Member with id = " + id + " not found");
        }
    }

}
