package com.skillmagnet.Quiz.Results;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillmagnet.Quiz.Quiz;
import com.skillmagnet.Quiz.QuizRepository;
import com.skillmagnet.Quiz.Question.Question;
import com.skillmagnet.Quiz.Question.QuestionRepository;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOption;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOptionRepository;
import com.skillmagnet.Quiz.Results.QuestionResult.QuestionResult;
import com.skillmagnet.Quiz.Results.QuestionResult.QuestionResultRepository;
import com.skillmagnet.Quiz.Results.UserSubmission.Answer;
import com.skillmagnet.User.User;
import com.skillmagnet.User.UserRepository;

import lombok.Builder;
import lombok.Getter;


/**
 * Includes functionality to grade user submissions, as well as generate grade reports
 * aka results for the quiz (which questions correct, which questions wrong)
 */
@Service
public class GradingService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    private QuestionOptionRepository questionOptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionResultRepository questionResultRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Getter
    @Builder
    public static class GradeResponse {
        private int score;
        private int scorePossible;
        List<QuestionResult> results;
    }

    /**
     * Goes through each question submitted and creates QuestionResults to be stored
     * 
     * Handles both MCQ and SA questions, must be ran for user and quiz before a grade report
     * can be generated
     * 
     * @param submission
     */
    public void gradeSubmission(UserSubmission submission){
        User user = userRepository.findById(submission.getUserId());
        List<Answer> answers = submission.getAnswers();
        for(Answer answer : answers){
            Question question = questionRepository.findById(answer.getQuestionId()).get();

            // check if user has completed this question before,
            // we will update that response instead of creating a new one to avoid duplication
            QuestionResult lastResult = questionResultRepository.findByUserAndQuestion(user, question);
            
            switch (question.getQuestionType()) {
                // Grade Multiple Choice Questions
                case MCQ:
                    // Their answer for MCQ
                    QuestionOption optionChosen = questionOptionRepository.findById(answer.getQuestionOptionId()).get();
                    // Update Result
                    if(lastResult != null){
                        lastResult.setQuestionOption(optionChosen);
                        lastResult.setQuestionOptionText(optionChosen.getOptionText());
                        lastResult.setCorrect((optionChosen.getIsCorrect()));
                        questionResultRepository.save(lastResult);
                        break;
                    }
                    // Create New Result
                    // First time taking quiz
                    QuestionResult result = QuestionResult.builder()
                            .question(question)
                            .user(user)
                            .shortAnswer("N/A")
                            .questionOption(optionChosen)
                            .questionOptionText(optionChosen.getOptionText())
                            .isCorrect(optionChosen.getIsCorrect()) 
                            .build();
                    questionResultRepository.save(result);
                    break;
                // Grade Short Answer Questions
                case SA:   
                    boolean isCorrectSA = question.getCorrectShortAnswer().toLowerCase().equals(
                        answer.getShortAnswer().toLowerCase());
                    
                    // Update Old Result
                    if(lastResult != null){
                        lastResult.setShortAnswer(answer.getShortAnswer());
                        lastResult.setCorrect(isCorrectSA);
                        questionResultRepository.save(lastResult);
                        break;
                    }
                    // Create new result
                    result = QuestionResult.builder()
                        .question(question)
                        .user(user)
                        .shortAnswer(answer.getShortAnswer())
                        .questionOption(null)
                        .questionOptionText("N/A")
                        .isCorrect(isCorrectSA) 
                        .build();
                    questionResultRepository.save(result);
                    break;
            }

        }
    }
    /**
     * Generates a Grade Report to show what questions the user got correct/incorrect
     * Used for feedback
     * @param userId
     * @param quizId
     * @return
     * {
     *  "score": 1, 
     *  "scorePossible": 2,
     *  "results": [
     *        {
     *        "resultId": 1,
     *        "shortAnswer": "N/A",
     *        "questionOptionText": "GREEN80", // this was the option they selected for MCQ
     *        "correct": false
     *        },
     *        {
     *        "resultId": 2,
     *        "shortAnswer": "this was their short answer",
     *        "questionOptionText": null,
     *        "correct": true
     *        }
     *      ]
     *    }
     */
    public GradeResponse generateGradedResponse(int userId, int quizId){
        User u = userRepository.findById(userId);
        Quiz quiz = quizRepository.findById(quizId).get();
        int score = 0;
        int scorePossible = 0;
        List<QuestionResult> generatedResults = new ArrayList<>();

        // Iterate through quizzes available questions
        // find question result for user from QuestionResult table
        // calculate final score
        for (Question q : quiz.getQuestions()){
            scorePossible += 1;
            QuestionResult result = questionResultRepository.findByUserAndQuestion(u, q);
            generatedResults.add(result);
            if(result.isCorrect()){
                score += 1;
            }
        }
        
        GradeResponse gradeResponse = GradeResponse.builder()
                                        .score(score)
                                        .results(generatedResults)
                                        .scorePossible(scorePossible)
                                        .build();
        return gradeResponse;
    }
}
