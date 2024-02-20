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

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
        // Username taken check
        if(userRepository.findByUsername(username) == null) {
            User newUser = new User(username, password);

            userRepository.save(newUser);

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam("username") String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestParam("username") String username,
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