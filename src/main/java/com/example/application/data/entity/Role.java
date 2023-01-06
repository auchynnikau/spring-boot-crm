package com.example.application.data.entity;

import javax.persistence.*;

@Entity
public class Role extends AbstractEntity {
    private String role;

    public Role() { }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
