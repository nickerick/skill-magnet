package com.skillmagnet.Enrolls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillmagnet.CompletedLesson.CompletedLesson;
import com.skillmagnet.CompletedLesson.CompletedLessonRepository;
import com.skillmagnet.Course.Course;
import com.skillmagnet.Course.CourseRepository;
import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.Lesson.LessonRepository;
import com.skillmagnet.User.User;
import com.skillmagnet.User.UserRepository;

@RestController
@RequestMapping("api")
public class EnrollsController {
    /*
     * Using all repository's to find all objects involved in relation
     */

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnrollsRepository enrollsRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CompletedLessonRepository completedLessonRepository;

    /**
     * Find's all enrollments based on user id
     * 
     * Each item in the return list displays the full course object
     * @param id - user id
     * @return a List of enrollments
     */
    @GetMapping("/enrolls/user/{uid}")
    public ResponseEntity<?> getUserEnrolledCourses(@PathVariable("uid") int id){
        User requestedUser = userRepository.findById(id);
        if (requestedUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(enrollsRepository.findByEnrolledUser(requestedUser), HttpStatus.OK);
    }

    /**
     * Creates a new enrollment relation
     * 
     * Finds user and course from path variables & creates Enrollment obj
     * setting the relations to the found objects
     * @param uid - user ID
     * @param cid - course ID
     * @return - new Enrollment that's been saved
     */
    @PostMapping("/enrolls")
    public ResponseEntity<?> createEnrollment(@RequestBody EnrollsRequest enrollsRequest){
        // Find user and course objects and verify they exist/aren't null
        User enrolledUser = userRepository.findById(enrollsRequest.getUserId());
        Course enrolledCourse = courseRepository.findById(enrollsRequest.getCourseId());
        if(enrolledUser == null || enrolledCourse == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Create Relations and save enrollment object
        Enrolls newEnrollment = new Enrolls();
        newEnrollment.setEnrolledUser(enrolledUser);
        newEnrollment.setEnrolledCourse(enrolledCourse);
        enrollsRepository.save(newEnrollment);

        return new ResponseEntity<>(newEnrollment, HttpStatus.OK);
    }

    @DeleteMapping("/enrolls/user/{uid}/course/{cid}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable("uid") int uid, @PathVariable("cid") int cid){
        User enrolledUser = userRepository.findById(uid);
        Course enrolledCourse = courseRepository.findById(cid);
        Enrolls enrollment = enrollsRepository.findByEnrolledCourseAndEnrolledUser(enrolledCourse, enrolledUser); 
        if(enrolledUser == null || enrolledCourse == null || enrollment == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        enrollsRepository.delete(enrollment);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    /**
     * This endpoint is to be used when a user "finishes" a lesson
     * Finds all necessary objects (user,lesson,enrollment)
     * Creates new CompletedLesson object, added to enrollment completedLessons var
     * Sets progress for enrollment
     * 
     * @param requestData - {"lessonId": 1, "userId": 1}
     * @return
     *         Success: Enrollment Object & OK
     *         Failure: null & NOT_FOUND
     */
    @PutMapping("/enrolls/user/lesson")
    public ResponseEntity<?> addLessonCompleted(@RequestBody EnrollsLessonRequest requestData){
        Lesson lesson = lessonRepository.findById(requestData.getLessonId()).get();
        User user = userRepository.findById(requestData.getUserId());
        Enrolls enrollment = enrollsRepository.findByEnrolledCourseAndEnrolledUser(
            lesson.getCourse(), user);
        if(lesson == null || user == null || enrollment == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        CompletedLesson newLesson = new CompletedLesson();
        newLesson.setLesson(lesson);
        completedLessonRepository.save(newLesson);
        
        enrollment.addCompletedLessonAndSetProgress(newLesson);
        enrollsRepository.save(enrollment);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

}
