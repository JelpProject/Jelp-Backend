package com.cognixia.jump.springcloud.model;

import java.util.List;

public interface MemberProjection {
    
    Long getMbrId();
    String getFname();
    String getLname();
    String getUsername();

    List<Review> getReviews();
    void setReviews(List<Review> reviews);

}
