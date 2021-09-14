package com.cognixia.jump.springcloud.model;

public class MemberDto {

    private Long mbrId;
    private String fname;
    private String lname;
    private String username;

    public MemberDto(Long mbrId, String fname, String lname, String username) {
        this.mbrId = mbrId;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
    }

    public Long getMbrId() {
        return this.mbrId;
    }

    public void setMbrId(Long mbrId) {
        this.mbrId = mbrId;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
            " mbrId='" + getMbrId() + "'" +
            ", fname='" + getFname() + "'" +
            ", lname='" + getLname() + "'" +
            ", username='" + getUsername() + "'" +
            "}";
    }

    
}
