package com.example.powerseva1.entity;

import jakarta.persistence.*;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String wardNumber;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }
}