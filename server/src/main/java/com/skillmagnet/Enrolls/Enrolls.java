package com.skillmagnet.Enrolls;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.skillmagnet.Course.Course;
import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.User.User;

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

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName="id")
    private User enrolledUser;
    
    public Enrolls() {
        this.progress = 0; //sets progress to 0 on default during creation
    }

    /**
     * Returns new progress calculated by 
     * (lessonsFinished/lessonsAvailableInCourse)
     * @param u - user enrolled
     * @param c - course enrolled in
     * @return - whole integer 1-100 to be represented as a percentage
     */
    public int calculateProgress(User u, Course c){
        List<Lesson> courseLessons = c.getLessons();
        Set<Lesson> userLessons = u.getLessonsCompleted();
        int completedLessonsCount = 0;
        // Iterate through the lessons of the course
        for (Lesson lesson : courseLessons) {
            // Check if the user has completed the lesson
            if (userLessons.contains(lesson)) {
                completedLessonsCount++;
            }
        }
        return (int) Math.round((double) completedLessonsCount / courseLessons.size() * 100);
    }

}
