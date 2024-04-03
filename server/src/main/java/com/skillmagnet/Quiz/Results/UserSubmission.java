package com.skillmagnet.Quiz.Results;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    
