package com.skillmagnet.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class CourseController {

    /**
     * hooks up to sql db for operations
     */
    @Autowired
    CourseRepository courseRepository;

    /**
     * Retrieves specific course by id
     *
     * @param id - id of course to find
     * @return - course object || NOT_FOUND
     */
    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") int id) {
        Course course = courseRepository.findById(id);

        if (course == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    /**
     * @return - all courses in course table in a list
     */
    @GetMapping("/course")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Create operation for course
     *
     * @param course - request body of json course object
     *               <p>
     *               IE:
     *               {
     *               "title": "First Course - Updated!",
     *               "description": "Testing out new api - Updated!",
     *               "category": "Testing - Updated!",
     *               "views": 0,
     *               "thumbsUp": 0,
     *               "thumbsDown": 0
     *               }
     * @return course object || bad request for missing title, category, and/or description
     */
    @PostMapping("/course")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        if (null == course.getTitle() || null == course.getCategory() || null == course.getDescription()) {
            return new ResponseEntity<>("Course does not have all required fields", HttpStatus.BAD_REQUEST);
        }

        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    /**
     * Delete course
     *
     * @param id - course to delete id
     * @return OK || NOT_FOUND
     */
    @DeleteMapping("/course/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") int id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        courseRepository.delete(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    /**
     * Updates entire Course object, any new changes to request body given will be applied to existing row/object
     *
     * @param id            - id of course to update
     * @param updatedCourse - body of request, json object
     * @return - updated course || not_found
     */
    @PutMapping("/course/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int id, @RequestBody Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id);
        if (existingCourse == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // update existing object
        existingCourse.setCategory(updatedCourse.getCategory());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setViews(updatedCourse.getViews());
        existingCourse.setThumbsDown(updatedCourse.getThumbsDown());
        existingCourse.setThumbsUp(updatedCourse.getThumbsUp());

        // save and return new object
        courseRepository.save(existingCourse);
        return new ResponseEntity<>(existingCourse, HttpStatus.OK);
    }

    /**
     * Takes in id and automatically increments course view count by 1
     *
     * @param id
     * @return on success - new course, On failure - NOT_FOUND
     */
    @PutMapping("/course/view/{id}")
    public ResponseEntity<Course> updateCourseView(@PathVariable("id") int id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // increment view, save, and return updated info
        course.setViews(course.getViews() + 1);
        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
