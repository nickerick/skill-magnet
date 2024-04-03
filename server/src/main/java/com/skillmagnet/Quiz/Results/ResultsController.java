package com.skillmagnet.Quiz.Results;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillmagnet.Quiz.Results.GradingService.GradeResponse;

@RestController
@RequestMapping("api")
public class ResultsController {

    @Autowired
    private GradingService gradingService;

    @PostMapping("/results/submit")
    public ResponseEntity<?> submitResultsForQuiz(@RequestBody UserSubmission submission){
        try {
            gradingService.gradeSubmission(submission);
            GradeResponse gr = gradingService.generateGradedResponse(submission.getUserId(), submission.getQuizId());
            return new ResponseEntity<>(gr, HttpStatus.OK);
        } 
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/results/user/{userId}/quiz/{quizId}")
    public ResponseEntity<?> getResultsForUserQuiz(@PathVariable int userId, @PathVariable int quizId){
        try {
            return new ResponseEntity<>(gradingService.generateGradedResponse(userId, quizId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
