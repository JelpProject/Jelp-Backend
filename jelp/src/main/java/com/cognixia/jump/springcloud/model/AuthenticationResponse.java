package com.cognixia.jump.springcloud.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private final String jwt;

    private String fname;
    private String lname;
    private String username;
    private Boolean isAdmin;
    private Long mbrId;

    public AuthenticationResponse(String jwt, MemberDto user) {
        this.jwt = jwt;
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.username = user.getUsername();
        this.isAdmin = user.getIsAdmin();
        this.mbrId = user.getMbrId();
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public Long getMbrId() {
        return mbrId;
    }

}
