package com.cognixia.jump.springcloud.service;

import java.util.ArrayList;
import java.util.Optional;

import com.cognixia.jump.springcloud.model.Member;
import com.cognixia.jump.springcloud.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository mbrRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Member> found = mbrRepo.findByUsername(username);

        // if member not found
        if (found.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        // Creates a new user in the spring security
        return new User(found.get().getUsername(), "{noop}" + found.get().getPassword(), new ArrayList<>());

    }
    
}
