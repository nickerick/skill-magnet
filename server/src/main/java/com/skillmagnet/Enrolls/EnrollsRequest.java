package com.skillmagnet.Enrolls;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

/**
 * Simple class that mimics the request body for creating a new enrollment
 * Sample json request body
 * {
 *  "courseId": 1,
 *  "userId": 1
 * }
 */
@Getter
public class EnrollsRequest {
    @Positive(message = "Must supply valid course id")
    private int courseId;
    
    @Positive(message = "Must supply valid user id")
    private int userId;
}       
