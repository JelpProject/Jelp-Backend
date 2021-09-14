package com.cognixia.jump.springcloud.controller;

import com.cognixia.jump.springcloud.model.AuthenticationRequest;
import com.cognixia.jump.springcloud.model.AuthenticationResponse;
import com.cognixia.jump.springcloud.service.MyUserDetailsService;
import com.cognixia.jump.springcloud.util.JwtUtil;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
   

	@Autowired
    private AuthenticationManager manager;

    @Autowired
    private MyUserDetailsService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateTokens(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
        
    }


}
