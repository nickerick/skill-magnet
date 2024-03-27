package com.skillmagnet.CompletedLesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skillmagnet.Lesson.Lesson;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "completedLesson")
public class CompletedLesson {
    @Id
    @JsonIgnore // id is uneeded, and clunks up response
    @GeneratedValue
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "lid", referencedColumnName = "id")
    private Lesson lesson;
}
