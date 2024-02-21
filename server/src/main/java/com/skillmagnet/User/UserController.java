package com.skillmagnet.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api")
public class UserController  {

    @Autowired
    UserRepository userRepository;

    /**
     * Creates a new user out of the given username and password.
     * @param username - string containing username
     * @param password - string containing plaintext password
     * @return
     *          Success - user object & OK
     *          Failure - "Duplicate Username" & BAD_REQUEST
     *          Error - "Failed to Create User" & OK
     */
    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody String username,
                                        @RequestBody String password) {
        // Duplicate Check
        if(userRepository.findByUsername(username) != null) {
            return new ResponseEntity<>("Duplicate Username", HttpStatus.BAD_REQUEST);
        }

        try {
            User newUser = new User(username, password);

            userRepository.save(newUser);

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Failed to Create User", HttpStatus.OK);
        }
    }

    /**
     * Retrieve a user by username.
     * @param username - user's unique username string
     * @return
     *          Success: User Object & OK
     *          Failure: null & NOT_FOUND
     */
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestBody String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

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
     * Compares login credentials to stored credentials.
     * @param unAuthorizedUser - unauthenticated user object containing the username and password strings to compare.
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
}