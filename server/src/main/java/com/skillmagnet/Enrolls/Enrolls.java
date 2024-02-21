package com.skillmagnet.Enrolls;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.skillmagnet.Course.Course;
import com.skillmagnet.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "enrolls")
public class Enrolls {
    @Id
    @GeneratedValue
    private int id;

    @CreationTimestamp
    private LocalDateTime enrolledAt;
    private int progress;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Course enrolledCourse;
    //@JoinTable
    private User enrolledUser;
    

    public Enrolls() {}

    // public Enrolls()
}
