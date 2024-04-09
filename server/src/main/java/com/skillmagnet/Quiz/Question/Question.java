package com.skillmagnet.Quiz.Question;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skillmagnet.Quiz.Quiz;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOption;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Main Question type, stores the question text
 * as well as relations with the corresponding quiz 
 * and multiple choice options if applicable
 */
@Entity
@Table(name = "Question")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    @Id
    @GeneratedValue
    private int questionId;

    @JsonIgnore
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
