package com.cognixia.jump.springcloud.model;

public class RestaurantDto {
    
    private Long restaurantId;
    private String name;


    public RestaurantDto(Long restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public Long getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
            " restaurantId='" + getRestaurantId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
    

}
