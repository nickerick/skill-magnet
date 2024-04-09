package com.skillmagnet.Quiz.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillmagnet.Quiz.Quiz;
import com.skillmagnet.Quiz.QuizRepository;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOption;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOptionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionOptionRepository questionOptionRepository;

    /**
     * [
        {
            "quizId": 1,
            "questionText": "Best Football callout?",
            "questionType": "MCQ",
            "correctShortAnswer": "N/A",
            "options": [
                {
                    "optionText": "BLUE42",
                    "isCorrect": true
                },
                {
                    "optionText": "GREEN80",
                    "isCorrect": false
                }
            ]
        },
        {
            "quizId": 1,
            "questionText": "SHORT ANSWER",
            "questionType": "SA",
            "correctShortAnswer": "YES",
            "options": []
        }
       ]
     * @param questions
     * @return
     */
    @PostMapping("/question")
    public ResponseEntity<List<Question>> createQuestionsForQuiz(@RequestBody List<QuestionRequest> questions) {
        List<Question> addedQuestions = new ArrayList<>(); // used for return

        // Create Question Objects out of each item in list
        for(QuestionRequest question : questions){
            Optional<Quiz> quiz = quizRepository.findById((question.getQuizId()));
            if(quiz.isPresent()){
                Question newQuestion = question.toQuestion(quiz.get()); //pass in quiz relation
                addedQuestions.add(newQuestion);
                questionRepository.save(newQuestion);
               
                // If its a multiple choice ? set the multiple choice options to 
                // reference the newly created question
                if(newQuestion.getQuestionType() == QuestionType.MCQ){
                    for(QuestionOption questionOpt : newQuestion.getOptions()){
                        questionOpt.setQuestion(newQuestion);
                        questionOptionRepository.save(questionOpt);
                    }
                }
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<List<Question>>(addedQuestions, HttpStatus.OK);
    }

}
