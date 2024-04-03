package com.skillmagnet.Quiz.Question;

import java.util.List;

import com.skillmagnet.Quiz.Quiz;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOption;

import lombok.Builder;
import lombok.Getter;


/**
 * Used in QuestionController
 * 
 * Allows for passing in quizId in requestbody
 * 
 * includes builder to create new Question Objects with other information in request body
 */
@Getter
@Builder
public class QuestionRequest {
    private int quizId;
    private String questionText;
    private QuestionType questionType;
    private String correctShortAnswer;
    private List<QuestionOption> options;

    Question toQuestion(Quiz quiz){
        return Question.builder()
            .questionText(questionText)
            .questionType(questionType)
            .correctShortAnswer(correctShortAnswer)
            .options(options)
            .quiz(quiz)
            .build();
    }
}
