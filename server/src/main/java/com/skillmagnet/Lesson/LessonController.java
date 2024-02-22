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

    /**
     * Retrieves a lesson by its ID.
     *
     * @param id The ID of the lesson to retrieve.
     * @return {@code Lesson} on success otherwise a NOT_FOUND status.
     */
    @GetMapping("/lessons/{id}")
    public ResponseEntity<?> getLesson(@PathVariable("id") int id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);

        if (lesson.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
    }

    /**
     * Retrieves all lessons.
     *
     * @return ResponseEntity with a list of all lessons.
     */
    @GetMapping("/lessons")
    public ResponseEntity<?> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    /**
     * Creates a new lesson.
     *
     * @param newLesson The lesson to be created, provided in the request body.
     * @return The created course on success otherwise
     * - 404 with message "Course does not exist"
     * - 400 with message "Duplicate video number"
     */
    @PostMapping("/lessons")
    public ResponseEntity<?> createLesson(@Valid @RequestBody LessonRequest newLesson) {
        Course course = courseRepository.findById(newLesson.getCourseId());
        if (null == course) {
            return new ResponseEntity<>("Course does not exist", HttpStatus.NOT_FOUND);
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

    /**
     * Updates an existing lesson.
     *
     * @param id        The ID of the lesson to update.
     * @param newLesson The updated lesson data provided in the request body.
     * @return The updated lesson or a NOT_FOUND status if the lesson is not found.
     */
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

    /**
     * Deletes a lesson by its ID.
     *
     * @param id The ID of the lesson to delete.
     * @return The deleted lesson or a NOT_FOUND status if the lesson is not found.
     */
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
