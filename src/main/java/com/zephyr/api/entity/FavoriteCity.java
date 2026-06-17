package com.zephyr.api.entity;

import jakarta.persistence.*;

@Entity
public class FavoriteCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String cityName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public FavoriteCity() {}

    public FavoriteCity(String cityName, User user) {
        this.cityName = cityName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
