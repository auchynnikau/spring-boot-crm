package com.example.application.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {
    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Role() {

    }

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
