package com.example.application.data.entity;

import javax.persistence.Entity;

@Entity
public class Role extends AbstractEntity {
    private String role;

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    public String getName() {
        return role;
    }

    public void setName(String role) {
        this.role = role;
    }

}
