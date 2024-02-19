package com.skillmagnet.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserController  {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
        User newUser = new User(username, password);

        userRepository.save(newUser);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam("username") String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestParam("username") String username,
                                              @RequestParam("password") String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if(user.passwordMatch(password)) {
            return new ResponseEntity<>("Passwords Match!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Passwords do not match!", HttpStatus.OK);
        }
    }
}