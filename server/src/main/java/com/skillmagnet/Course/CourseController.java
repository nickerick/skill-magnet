package com.skillmagnet.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
public class CourseController {
    
    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/course")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<Course> getCourse(@RequestParam("id") int id){
        Course course = courseRepository.findById(id);

        if(course == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/course")
    public ResponseEntity<Course> deleteCourse(@RequestParam("id") int id){
        Course course = courseRepository.findById(id);
        if(course == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
        courseRepository.delete(course);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    

    @PutMapping("/course")
    public ResponseEntity<Course> updateCourse(@RequestParam("id") int id, @RequestBody Course course){
        courseRepository.findById(id);

        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
