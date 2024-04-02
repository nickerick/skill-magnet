package com.skillmagnet.Quiz.Question;

import java.util.List;

import com.skillmagnet.Quiz.Quiz;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Question")
@Data
public class Question {
    @Id
    @GeneratedValue
    private int questionId;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column
    private String questionText;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType; // Enum for MCQ, SA

    private String correctShortAnswer; // Used for short Answer
                                       // 'N/A' if questionType=MCQ

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionOption> options; // Used for Multiple Choice

}
