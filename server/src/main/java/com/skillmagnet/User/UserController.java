package com.skillmagnet.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillmagnet.Enrolls.Enrolls;
import com.skillmagnet.Enrolls.EnrollsRepository;
import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.Lesson.LessonRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api")
public class UserController  {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    EnrollsRepository enrollsRepository;

    /*-----------------< ENDPOINT LEAVING SOON >-----------------*/
    /**
     * Retrieve a user by username.
     * @param username - user's unique username string
     * @return
     *          Success: User Object & OK
     *          Failure: null & NOT_FOUND
     */
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    /*-----------------------------------------------------------*/

    /**
     * Retrieve a user by id.
     * @param id - user's unique int id
     * @return
     *          Success: User Object & OK
     *          Failure: null & NOT_FOUND
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userRepository.findById(id);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Creates a new user out of the given username and password.
     * @param userToCreate - JSON version of the user object containing only a username and password
     *                     - Input Format:
     *                          {
     *                              "username":"RonBurgundy6",
     *                              "passwordHash":"StayClassySD84"
     *                          }
     * @return
     *          Success - user object & OK
     *          Failure - "Duplicate Username" & BAD_REQUEST
     *          Error - "Failed to Create User" & OK
     */
    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody User userToCreate) {
        // Duplicate Check
        if(userRepository.findByUsername(userToCreate.getUsername()) != null) {
            return new ResponseEntity<>("Duplicate Username", HttpStatus.BAD_REQUEST);
        }

        try {
            User newUser = new User(userToCreate.getUsername(), userToCreate.getPasswordHash());

            userRepository.save(newUser);

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Failed to Create User", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Compares login credentials to stored credentials.
     * @param unAuthorizedUser - unauthenticated user object containing the username and password strings to compare.
     *                         - Same request body JSON format as the createUser() endpoint
     * @return
     *         Success: User Object & OK
     *         Mismatch: null & UNAUTHORIZED
     *         Failure: null & NOT_FOUND
     */
    @PostMapping("/user/login")
    public ResponseEntity<User> loginUser(@RequestBody User unAuthorizedUser) {
        User user = userRepository.findByUsername(unAuthorizedUser.getUsername());

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(!user.passwordMatch(unAuthorizedUser.getPasswordHash())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    /**
     * This endpoint is to be used when a user "finishes" a lesson
     * If requested lesson exists it's added to the users set of completed lessons
     * 
     * Tracking lessons completed allows us to be able to update course progress
     * in enrollment objects which is also completed here automatically
     * 
     * @param uid - user that finished course
     * @param lid - lesson they completed
     * @return
     *         Success: User Object & OK
     *         Failure: null & NOT_FOUND
     */
    @PostMapping("/user/lesson/{uid}/{lid}")
    public ResponseEntity<User> addLessonCompleted(@PathVariable("uid") int uid, @PathVariable("lid") int lid){
        // If either lesson or user doesn't exist, return not found
        User user = userRepository.findById(uid);
        Optional<Lesson> lessonToAdd = lessonRepository.findById(lid);
        if(lessonToAdd.isEmpty() || user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Here we grab the lessons completed set from the user to
        // make the second line more readable.
        // Since the lessonToaAdd is an optional we have to make sure it is
        // actually a Lesson with .isPresent(), then we pass isPresent an action
        // ie: add our new lesson to our list of completed lessons
        Set<Lesson> lessonsCompleted = user.getLessonsCompleted();
        lessonToAdd.ifPresent(lessonsCompleted::add);
        user.setLessonsCompleted(lessonsCompleted);

        // Find enrollment object to update progress
        // .map just allows to get the getCourse for lesson object from optional
        Enrolls userEnrollment = enrollsRepository
                .findByEnrolledCourseAndEnrolledUser(
                    lessonToAdd.map(Lesson::getCourse).orElse(null), // Course
                    user); // user
        if(userEnrollment == null){
            // A more detailed error probably needs to be here
            // This would be an issue if a user is completing a lesson in a course
            // they are not enrolled in
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // get new progress by passing in the user and course objects
        int newProgress = userEnrollment.calculateProgress(user, lessonToAdd.map(Lesson::getCourse).orElse(null));
        userEnrollment.setProgress(newProgress);
        

        // save all changes
        userRepository.save(user);
        enrollsRepository.save(userEnrollment);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}