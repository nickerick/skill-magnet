package com.skillmagnet.Enrolls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillmagnet.Course.Course;
import com.skillmagnet.Course.CourseRepository;
import com.skillmagnet.User.User;
import com.skillmagnet.User.UserRepository;

@RestController
@RequestMapping("api")
public class EnrollsController {
    
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnrollsRepository enrollsRepository;

    @GetMapping("/enrolls/{uid}")
    public ResponseEntity<List<Enrolls>> getUserEnrolledCourse(@PathVariable("uid") int id){
        User requestedUser = userRepository.findById(id);
        if (requestedUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(enrollsRepository.findByEnrolledUser(requestedUser), HttpStatus.OK);
    }

    @PostMapping("/enrolls/{uid}/{cid}")
    public ResponseEntity<Enrolls> createEnrollment(@PathVariable("uid") int uid, @PathVariable("cid") int cid){
        // Find user and course objects and create default enrollment
        User enrolledUser = userRepository.findById(uid);
        Course enrolledCourse = courseRepository.findById(cid);
        Enrolls newEnrollment = new Enrolls();

        // Create Relations and save enrollment object
        newEnrollment.setEnrolledUser(enrolledUser);
        newEnrollment.setEnrolledCourse(enrolledCourse);
        enrollsRepository.save(newEnrollment);

        return new ResponseEntity<>(newEnrollment, HttpStatus.OK);
    }
}
