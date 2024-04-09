package com.skillmagnet.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillmagnet.Course.Course;
import com.skillmagnet.Course.CourseRepository;
import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.Lesson.LessonRepository;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Endpoint for creating basic quiz, does not include the questions
     * {
     *  "title": "New Quiz".
     *  "description": "This is a sample quiz json obj"
     *  "lessonId": 1 // this creates the relation to the lesson obj
     * }
     * 
     * @param quizRequest
     * @return - SUCCESS new quiz object
     *           FAILURE 404 not found
     */
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

    /**
     * Return all quizzes for a course
     * 
     * Finds all lessons in course
     * Finds all quizzes in thsoe lessons
     * @param courseId
     * @return SUCCESS List<Quiz>
     *         FAILURE null & 404
     */
    @GetMapping("/quiz/course/{courseId}")
    public ResponseEntity<?> getAllQuizzesByCourse(@PathVariable int courseId){
        Course c = courseRepository.findById(courseId);
        List<Quiz> courseQuizzes = new ArrayList<>();
        if(c == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        List<Lesson> lessons = c.getLessons();
        for(Lesson lesson : lessons){
            Optional<List<Quiz>> quizzes = quizRepository.findByLesson(lesson);
            if(quizzes.isPresent()){
                courseQuizzes.addAll(quizzes.get());
            }  
        }
        return new ResponseEntity<>(courseQuizzes, HttpStatus.OK);
    }

    /**
     * Get all quizzes for specific lesson
     * @param lessonId
     * @return
     */
    @GetMapping("/quiz/lesson/{lessonId}")
    public ResponseEntity<?> getAllQuizzesByLesson(@PathVariable int lessonId){
        Optional<Lesson> l = lessonRepository.findById(lessonId);
        if(l.isPresent()){
            return new ResponseEntity<>(quizRepository.findByLesson(l.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /**
     * Sample Returned Quiz
     * 
     * {
        "quizId": 1,
        "createdAt": "2024-04-03T18:04:48.923666",
        "title": "Overview Quiz",
        "description": "Check Your Understanding - Overview",
        "questions": [
            { //SA
            "questionId": 1,
            "questionText": "What language will we be studying?",
            "questionType": "SA",
            "correctShortAnswer": "python",
            "options": []
            },
            { //MCQ
            "questionId": 2,
            "questionText": "What year was python invented?",
            "questionType": "MCQ",
            "correctShortAnswer": "N/A",
            "options": [
                {
                "optionId": 1,
                "optionText": "2001",
                "isCorrect": false
                },
                {
                "optionId": 2,
                "optionText": "1990",
                "isCorrect": false
                },
                {
                "optionId": 3,
                "optionText": "1998",
                "isCorrect": false
                },
                {
                "optionId": 4,
                "optionText": "1991",
                "isCorrect": true
                }
              ]
            }
        ]
        }
     * @param quizId
     * @return
     */
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
