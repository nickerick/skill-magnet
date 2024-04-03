package com.skillmagnet.Quiz.Results;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * Represents a user's answers for a quiz
 * Stores their id as well as the quiz id
 * and then a list of answers defined here
 */
@Getter
@Setter
public class UserSubmission {
    private int userId;
    private int quizId;
    private List<Answer> answers;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Answer {
        private int questionId;
        private String shortAnswer;
        private Integer questionOptionId;
    }
}
    
