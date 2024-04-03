package com.skillmagnet.Quiz.Results;

import java.util.NoSuchElementException;
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

    /**
     * Endpoint used for grading submissions
     * 
     * EXAMPLE SUBMISSION
     * 
     * {
        "userId": 1,
        "quizId": 1,
        "answers": [
             {
                "questionId": 52,
                "shortAnswer": "N/A",
                "questionOptionId": 2 // answered multiple choice
             },
             {
                "questionId": 53,
                "shortAnswer": "Heres my Short Answer!", // answered short answer
                "questionOptionId": 0
             }
            ]
        }
     * @param submission  - see above
     * @return
     */
    @PostMapping("/results/submit")
    public ResponseEntity<?> submitResultsForQuiz(@RequestBody UserSubmission submission){
        try {
            gradingService.gradeSubmission(submission);
            GradeResponse gr = gradingService.generateGradedResponse(submission.getUserId(), submission.getQuizId());
            return new ResponseEntity<>(gr, HttpStatus.OK);
        } 
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Submission Invalid", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns generated grade report including 
     * what questions incorrect/correct
     * @param userId - user to find
     * @param quizId - quiz to find
     * @return
     */
    @GetMapping("/results/user/{userId}/quiz/{quizId}")
    public ResponseEntity<?> getResultsForUserQuiz(@PathVariable int userId, @PathVariable int quizId){
        try {
            return new ResponseEntity<>(gradingService.generateGradedResponse(userId, quizId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User hasn't taken quiz yet", HttpStatus.NOT_FOUND);
        }
    }
}
