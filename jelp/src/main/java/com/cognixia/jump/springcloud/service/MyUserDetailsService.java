package com.cognixia.jump.springcloud.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.cognixia.jump.springcloud.model.Jmember;
import com.cognixia.jump.springcloud.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        
        Optional<Jmember> found = mbrRepo.findByUsername(username);

        // if member not found
        if (found.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        Jmember user = found.get();

        // Creates a new user in the spring security
        return new User(user.getUsername(), "{noop}" + user.getPassword(), getAuthority(user));

    }

    private Set<SimpleGrantedAuthority> getAuthority(Jmember user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return authorities;
    }
    
}
