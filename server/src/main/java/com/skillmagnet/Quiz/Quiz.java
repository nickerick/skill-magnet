package com.skillmagnet.Quiz;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.Quiz.Question.Question;

@Entity
@Table(name = "Quiz")
@Data
@Builder
public class Quiz {
    @Id
    @GeneratedValue
    private int quizId;

    @CreationTimestamp
    private LocalDateTime enrolledAt;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

}
