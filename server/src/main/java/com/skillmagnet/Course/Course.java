package com.skillmagnet.Course;

import java.sql.Time;
import java.sql.Timestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Course {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String description;
    private String category;
    private Timestamp createdAt;
    private Timestamp status;

    public Course() {}

    public Course(String title, String description, String category, Timestamp createdAt, Timestamp status){
        this.title = title;
        this.description = description;
        this.category = category;
        this.createdAt = createdAt;
        this.status = status;
    }

    // Getter and Setter for 'title'
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for 'description'
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for 'category'
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and Setter for 'createdAt'
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Getter and Setter for 'status'
    public Timestamp getStatus() {
        return status;
    }

    public void setStatus(Timestamp status) {
        this.status = status;
    }
    
}
