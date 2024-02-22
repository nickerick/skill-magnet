package com.skillmagnet.Lesson;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class LessonController {

    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/lessons/{id}")
    public ResponseEntity<?> getLesson(@PathVariable("id") int id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);

        if (lesson.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
    }

    @PostMapping("/lessons")
    public ResponseEntity<?> createLesson(@Valid @RequestBody LessonRequest lesson) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
