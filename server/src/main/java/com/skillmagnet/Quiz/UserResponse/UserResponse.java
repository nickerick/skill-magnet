package com.skillmagnet.Quiz.UserResponse;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.skillmagnet.Quiz.Question.Question;
import com.skillmagnet.Quiz.Question.QuestionOption;
import com.skillmagnet.User.User;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User_Responses")
@Data
public class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int responseId;

    @CreationTimestamp
    LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "selected_option_id")
    private QuestionOption selectedOption;

    private String shortAnswerText;

    private Boolean isCorrect;
}

