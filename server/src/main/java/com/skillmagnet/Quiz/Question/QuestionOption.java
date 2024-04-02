package com.skillmagnet.Quiz.Question;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "QuestionOptions")
@Data
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String optionText;
    private Boolean isCorrect;
}
