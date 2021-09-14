package com.cognixia.jump.springcloud.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private final String jwt;

    private String fname;
    private String lname;
    private String username;

    public AuthenticationResponse(String jwt, String fname, String lname, String username) {
        this.jwt = jwt;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return username;
    }

}
