package com.example.application.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Users extends AbstractEntity {
    private String login;

    private String password;


    @NotNull
    @ManyToOne
    private Role role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return role;
    }

    public void setUserRole(Role role) {
        this.role = role;
    }
}
