package com.skillmagnet.Quiz.Results.QuestionResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skillmagnet.Quiz.Question.Question;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOption;
import com.skillmagnet.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Tracks users answers for quizzes
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {
    @Id
    @GeneratedValue
    private int resultId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    
    private String shortAnswer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "option_id")
    private QuestionOption questionOption;

    private String questionOptionText;

    private boolean isCorrect;
}
