package com.skillmagnet.Enrolls;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.skillmagnet.CompletedLesson.CompletedLesson;
import com.skillmagnet.Course.Course;
import com.skillmagnet.User.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    // deletes the completedLessons not the actual lesson itself
    // just cleans up that table in event where enrollment was deleted
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enrollment_id")   
    private List<CompletedLesson> completedLessons;
    
    public Enrolls() {
        this.progress = 0; //sets progress to 0 on default during creation
    }

    /**
     * Adds newly completed lesson to this.completedLessons
     * Calculates and sets new progress for this.progress 
     * @param completedLesson - newly finished lesson
     */
    public void addCompletedLessonAndSetProgress(CompletedLesson completedLesson){
        this.completedLessons.add(completedLesson);
        // with new lesson compute new progress of enrollment
        // whole integer 1-100 to be represented as a percentage
        int completedCount = this.completedLessons.size();
        int availableCount = this.enrolledCourse.getLessons().size();
        int newProgress = (int) Math.round((double) completedCount / availableCount * 100);
        this.setProgress(newProgress);
    }
}