package com.example.movieweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`user`")
public class User {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "`username`",nullable = false,unique = true)
    String username;

    @Column(name = "`password`",nullable = false)
    String password;

    @Column(name = "`email`",nullable = false,unique = true)
    String email;

    @Column(name = "`admin`",nullable = false,columnDefinition = "boolean default false")
    Boolean admin;

    @Column(name = "`active`",nullable = false,columnDefinition = "boolean default true")
    Boolean active;

    @OneToMany(mappedBy = "user")
    List<History> histories;

    public User() {
    }

    public User(Integer id, String username, String password, String email, Boolean admin, Boolean active, List<History> histories) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.active = active;
        this.histories = histories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
