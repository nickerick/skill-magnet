package com.skillmagnet.Enrolls;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.skillmagnet.Course.Course;
import com.skillmagnet.User.User;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    /*
     * Each User and Course has a OneToMany 
     * association with Enrollment, 
     * representing that a user can have multiple enrollments 
     * and a course can have multiple enrollments.
     * 
     * name represents the column name in the enrolls table
     */


    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "id")
    private Course enrolledCourse;

    @Hidden
    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName="id")
    private User enrolledUser;
    
    public Enrolls() {
        this.progress = 0; //sets progress to 0 on default during creation
    }

}
