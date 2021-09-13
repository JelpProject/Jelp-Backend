package com.cognixia.jump.springcloud.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
    
    // key to use with our algorithm to hash/encode our token
    private final String SECRET_KEY = "jelp";

    // get the username for this token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // get expiration date for this token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // takes a token and a claims resolver to find what the claims are
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver ) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // checks if the token has expired yet by comparing the dates
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // return generated tokens after a successful authentication
    public String generateTokens(UserDetails userDetails) {

        // claims info/data we want to include in the payload of the token besides the user info
        Map<String, Object> claims = new HashMap<>();

        // returns token for user given along with any claims
        return createToken(claims, userDetails.getUsername());
    }

    // creates the token
    private String createToken(Map<String, Object> claims, String subject) {

        // set claims, the person being authenticated, and issue date
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt( new Date( System.currentTimeMillis() ) )
                // set when the token will expire (currently: 10hrs)
                .setExpiration( new Date( System.currentTimeMillis() + 1000 * 60 * 60 * 10 ) )
                // sign with particular algorithm and the secret key that lets us know token is authentic
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // validate the token and check if the current token is for the right user requesting it and that the token isn't expired
    public Boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUsername(token);

        return ( username.equals (userDetails.getUsername() ) && !isTokenExpired(token));

    }

}
