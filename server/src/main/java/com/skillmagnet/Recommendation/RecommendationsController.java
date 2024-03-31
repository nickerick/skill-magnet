package com.skillmagnet.Recommendation;

import com.skillmagnet.Course.Course;
import com.skillmagnet.Course.CourseRepository;
import com.skillmagnet.User.User;
import com.skillmagnet.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api")
public class RecommendationsController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseNodeRepository courseNodeRepository;

    /**
     * Generates course recommendations for a given user
     *
     * @param userId - user id
     * @return a List of courses in the order of most to least recommended
     */
    @GetMapping("/users/{uid}/recommendations")
    public ResponseEntity<?> getCourseRecommendations(@PathVariable("uid") int userId) {
        User requestedUser = userRepository.findById(userId);
        if (requestedUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        try {
            List<CourseNode> reccommenedCourseNodes = courseNodeRepository.getRecommendedCoursesForUser(userId);
            List<Course> recommendedCourses = new LinkedList<>();

            reccommenedCourseNodes.forEach(courseNode -> {
                Course courseMapping = courseRepository.findById(courseNode.getCourseId());
                recommendedCourses.add(courseMapping);
            });

            return new ResponseEntity<>(recommendedCourses, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Failed to get course recommendations for User " + userId);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
