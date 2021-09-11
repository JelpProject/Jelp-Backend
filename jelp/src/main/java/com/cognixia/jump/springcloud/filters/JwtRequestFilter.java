package com.cognixia.jump.springcloud.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.springcloud.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // retrieve the jwt sent with the request
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // if the token is there, grab only the token
            jwt = authorizationHeader.substring(7);

            // grab the user associated with the token
            // if the token is not valid, will return null
            username = jwtUtil.extractUsername(jwt);

        }

        // if we found teh user and not already in security context...
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // ... load in tehir details
            // UserDetails userDetails = this.userDetailsService.loadUserByUserName(username);

        }
        
    }
    
}
