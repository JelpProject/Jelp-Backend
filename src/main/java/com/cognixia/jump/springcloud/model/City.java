package com.cognixia.jump.springcloud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "City_Id")
    private Long cityId;

    @Column(name = "City_Name", columnDefinition = "varchar(45) not null")
    private String name;

    @Column(name = "State_Id")
    private Long stateId;

    @Transient
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private State cityState;

    public City() {
        this(-1L, "N/A/", -1L, new State());
    }

    public City(Long cityId, String name, Long stateId, State cityState) {
        this.cityId = cityId;
        this.name = name;
        this.stateId = stateId;
        this.cityState = cityState;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStateId() {
        return this.stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public State getCityState() {
        return this.cityState;
    }

    public void setCityState(State cityState) {
        this.cityState = cityState;
    }


    @Override
    public String toString() {
        return "{" +
            " cityId='" + getCityId() + "'" +
            ", name='" + getName() + "'" +
            ", stateId='" + getStateId() + "'" +
            ", cityState='" + getCityState() + "'" +
            "}";
    }

    
}
