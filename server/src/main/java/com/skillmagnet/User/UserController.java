package com.skillmagnet.User;

import com.skillmagnet.Enrolls.EnrollsRepository;
import com.skillmagnet.Lesson.LessonRepository;
import com.skillmagnet.Recommendation.UserNode;
import com.skillmagnet.Recommendation.UserNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    EnrollsRepository enrollsRepository;

    @Autowired
    UserNodeRepository userNodeRepository;

    /*-----------------< ENDPOINT LEAVING SOON >-----------------*/

    /**
     * Retrieve a user by username.
     *
     * @param username - user's unique username string
     * @return Success: User Object & OK
     * Failure: null & NOT_FOUND
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
     *
     * @param id - user's unique int id
     * @return Success: User Object & OK
     * Failure: null & NOT_FOUND
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
     *
     * @param userToCreate - JSON version of the user object containing only a username and password
     *                     - Input Format:
     *                     {
     *                     "username":"RonBurgundy6",
     *                     "passwordHash":"StayClassySD84"
     *                     }
     * @return Success - user object & OK
     * Failure - "Duplicate Username" & BAD_REQUEST
     * Error - "Failed to Create User" & OK
     */
    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody User userToCreate) {
        // Duplicate Check
        if (userRepository.findByUsername(userToCreate.getUsername()) != null) {
            return new ResponseEntity<>("Duplicate Username", HttpStatus.BAD_REQUEST);
        }

        try {
            User newUser = new User(userToCreate.getUsername(), userToCreate.getPasswordHash());

            userRepository.save(newUser);

            try {
                UserNode userNode = new UserNode(newUser.getId(), newUser.getUsername());
                userNodeRepository.save(userNode);
            } catch (Exception e) {
                System.out.println("GraphDBError: Failed to create User " + newUser.getId());
            }

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Create User", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Compares login credentials to stored credentials.
     *
     * @param unAuthorizedUser - unauthenticated user object containing the username and password strings to compare.
     *                         - Same request body JSON format as the createUser() endpoint
     * @return Success: User Object & OK
     * Mismatch: null & UNAUTHORIZED
     * Failure: null & NOT_FOUND
     */
    @PostMapping("/user/login")
    public ResponseEntity<User> loginUser(@RequestBody User unAuthorizedUser) {
        User user = userRepository.findByUsername(unAuthorizedUser.getUsername());

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (!user.passwordMatch(unAuthorizedUser.getPasswordHash())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}