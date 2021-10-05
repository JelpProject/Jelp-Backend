package com.cognixia.jump.springcloud.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.springcloud.service.MyUserDetailsService;
import com.cognixia.jump.springcloud.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // check request header for valid jwt to access the API its requesting
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // retrieve the jwt sent with the request
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // check for header content and jwt
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // if the token is there, grab only the token
            jwt = authorizationHeader.substring(7);

            // grab the user associated with the token
            // if the token is not valid, will return null
            username = jwtUtil.extractUsername(jwt);

        }

        // if we found the user and not already in security context...
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // ... load in their details
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // check token is valid and jwt has not expired
            if (jwtUtil.validateToken(jwt, userDetails)) {
                // store authenticated user in security context
                UsernamePasswordAuthenticationToken unamePassAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                unamePassAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(unamePassAuthenticationToken);
            }

        }

        filterChain.doFilter(request, response);
        
    }
    
}
