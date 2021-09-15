package com.cognixia.jump.springcloud.model;

public class MemberDto {

    private Long mbrId;
    private String fname;
    private String lname;
    private String username;
    private Boolean isAdmin;

    public MemberDto(Long mbrId, String fname, String lname, String username, Boolean isAdmin) {
        this.mbrId = mbrId;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public Long getMbrId() {
        return this.mbrId;
    }

    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public String getUsername() {
        return this.username;
    }

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    @Override
    public String toString() {
        return "{" +
            " mbrId='" + getMbrId() + "'" +
            ", fname='" + getFname() + "'" +
            ", lname='" + getLname() + "'" +
            ", username='" + getUsername() + "'" +
            ", isAdmin='" + getIsAdmin() + "'" +
            "}";
    }

    
}
