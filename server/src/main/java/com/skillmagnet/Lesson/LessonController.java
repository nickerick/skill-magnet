package com.skillmagnet.Lesson;

import com.skillmagnet.Course.Course;
import com.skillmagnet.Course.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class LessonController {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/lessons/{id}")
    public ResponseEntity<?> getLesson(@PathVariable("id") int id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);

        if (lesson.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
    }

    @GetMapping("/lessons")
    public ResponseEntity<?> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @PostMapping("/lessons")
    public ResponseEntity<?> createLesson(@Valid @RequestBody LessonRequest newLesson) {
        Course course = courseRepository.findById(newLesson.getCourseId());
        if (null == course) {
            return new ResponseEntity<>("Course does not exist", HttpStatus.BAD_REQUEST);
        }

        List<Lesson> lessons = course.getLessons();

        boolean isDuplicateVideoNumber = lessons.stream().map(Lesson::getVideoNumber).toList().contains(newLesson.getVideoNumber());
        if (isDuplicateVideoNumber) {
            return new ResponseEntity<>("Duplicate video number", HttpStatus.BAD_REQUEST);
        }

        Lesson lesson = newLesson.toLesson(course);
        lessonRepository.save(lesson);

        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable int id, @RequestBody LessonRequest newLesson) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if (null == lesson) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String newVideoTitle = newLesson.getTitle();
        if (null != newVideoTitle && !newVideoTitle.equals(lesson.getTitle())) {
            lesson.setTitle(newVideoTitle);
        }

        VideoType newVideoType = newLesson.getVideoType();
        if (null != newVideoType && newVideoType != lesson.getVideoType()) {
            lesson.setVideoType(newVideoType);
        }

        String newVideoLink = newLesson.getVideoLink();
        if (null != newVideoLink && newVideoLink != lesson.getVideoLink()) {
            lesson.setVideoLink(newVideoLink);
        }

        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable("id") int id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);

        if (lesson.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lessonRepository.delete(lesson.get());

        return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
    }
}
