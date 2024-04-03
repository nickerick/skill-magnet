package com.skillmagnet.Quiz.Question.QuestionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skillmagnet.Quiz.Question.Question;

import jakarta.persistence.*;
import lombok.Data;

/**
 * This table represents multiple choice options
 */
@Entity
@Table(name = "QuestionOptions")
@Data
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String optionText;
    private Boolean isCorrect;
}
