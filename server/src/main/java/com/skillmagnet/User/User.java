package com.skillmagnet.User;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this.id;
    }

}