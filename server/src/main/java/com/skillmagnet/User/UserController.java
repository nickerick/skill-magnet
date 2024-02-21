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
     *          Success - The Created User
     *          Failure - BAD_REQUEST (Username Taken)
     */
    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
        // Username taken check
        if(userRepository.findByUsername(username) == null) {
            User newUser = new User(username, password);

            userRepository.save(newUser);

            return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * Retrieve a user by username.
     * @param username - user's unique username string
     * @return
     *          Success: Requested User
     *          Failure: NOT_FOUND
     */
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam("username") String username) {
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
     *          Success: Requested User
     *          Failure: NOT_FOUND
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
     * @param username - name of user attempting login
     * @param password - password provided at login (Plaintext)
     * @return
     *         Success(Match): "Passwords Match!"
     *         Success(Mismatch): "Passwords do not match!"
     *         Failure: NOT_FOUND
     */
    @PutMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestParam("username") String username,
                                              @RequestParam("password") String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if(user.passwordMatch(password)) {
            user.setLast_login(LocalDateTime.now());
            userRepository.save(user);
            return new ResponseEntity<>("Passwords Match!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Passwords do not match!", HttpStatus.OK);
    }
}