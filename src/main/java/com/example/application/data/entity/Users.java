package com.example.application.data.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users extends AbstractEntity {
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

//    @Column(name = "enabled", nullable = false)
//    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Role> role;

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
        return (Role) role;
    }

    public void setUserRole(Role role) {
        this.role = (Set<Role>) role;
    }
}
