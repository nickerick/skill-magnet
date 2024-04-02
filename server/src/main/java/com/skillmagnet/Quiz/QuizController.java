package com.skillmagnet.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.Lesson.LessonRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @PostMapping("/quiz")
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody QuizRequest quizRequest) {
        Optional<Lesson> lesson = lessonRepository.findById(quizRequest.getLessonId());
        if(lesson.isPresent()){
            Quiz newQuiz = quizRequest.toQuiz(lesson.get());
            quizRepository.save(newQuiz);
            return new ResponseEntity<>(newQuiz, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/quiz")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if(quiz.isPresent())
        {
            return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
