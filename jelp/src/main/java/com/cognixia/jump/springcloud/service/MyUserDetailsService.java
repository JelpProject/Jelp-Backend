package com.cognixia.jump.springcloud.service;

import java.util.HashSet;
import java.util.Set;

import com.cognixia.jump.springcloud.model.Member;
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
        
        Member user = mbrRepo.findByUsername(username, Member.class);

        // if member not found
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // Member user = found.get();

        // Creates a new user in the spring security
        return new User(user.getUsername(), "{noop}" + user.getPassword(), getAuthority(user));

    }

    private Set<SimpleGrantedAuthority> getAuthority(Member user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return authorities;
    }
    
}
