package com.skillmagnet.Quiz.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResponseRepository extends JpaRepository<UserResponse, Integer> {
    // Custom query methods can be defined here
}
