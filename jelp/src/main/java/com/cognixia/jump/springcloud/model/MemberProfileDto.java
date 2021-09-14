package com.cognixia.jump.springcloud.model;

import java.util.List;

public class MemberProfileDto extends MemberDto {
    
    // additional attributes not provided in MemberDto
    List<Review> reviews;

    public MemberProfileDto(Long mbrId, String fname, String lname, String username, List<Review> reviews) {
        super(mbrId, fname, lname, username);
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "{" +
            " mbrId='" + getMbrId() + "'" +
            ", fname='" + getFname() + "'" +
            ", lname='" + getLname() + "'" +
            ", username='" + getUsername() + "'" +
            " reviews='" + getReviews() + "'" +
            "}";
    }
}
