package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "City_Id")
    private Long cityId;

    @Column(name = "City_Name", columnDefinition = "varchar(45) not null")
    private String name;

    @ManyToOne
    @JoinColumn(name="State_Id", nullable = false, insertable = false, updatable = false)
    private State cityState;

    @Transient
    @OneToMany
    @JoinColumn(name="Mbr_Id")
    private List<Member> users;

    public City() {
        this(-1L, "N/A/", new State());
    }

    public City(Long cityId, String name, State cityState) {
        this.cityId = cityId;
        this.name = name;
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
            ", cityState='" + getCityState() + "'" +
            "}";
    }

    
}
