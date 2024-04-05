package com.skillmagnet;

import com.skillmagnet.Course.Course;
import com.skillmagnet.Course.CourseRepository;
import com.skillmagnet.Enrolls.Enrolls;
import com.skillmagnet.Enrolls.EnrollsRepository;
import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.Lesson.LessonRepository;
import com.skillmagnet.Lesson.VideoType;
import com.skillmagnet.User.User;
import com.skillmagnet.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private EnrollsRepository enrollsRepository;

    @Value("${data.generation.enabled:false}")
    private boolean dataGenerationEnabled;

    @Override
    public void run(String[] args) {
        if (dataGenerationEnabled) {
            System.out.println("Generating data...");


            // Create Users
            User user1 = new User("Test User", "password");
            userRepository.save(user1);

            User user2 = new User("John Doe", "Tigers2!");
            userRepository.save(user2);


            // Create Course and Lessons
            Course course1 = new Course();
            course1.setTitle("Python 101");
            course1.setDescription("Learn Python for complete beginners!");
            course1.setCategory("Programming");
            courseRepository.save(course1);

            Lesson lesson1 = new Lesson();
            lesson1.setTitle("Overview");
            lesson1.setVideoType(VideoType.SELF_HOSTED);
            lesson1.setVideoNumber(1);
            lesson1.setCourse(course1);
            lessonRepository.save(lesson1);

            Lesson lesson2 = new Lesson();
            lesson2.setTitle("Introduction to Python");
            lesson2.setVideoType(VideoType.YOUTUBE);
            lesson2.setVideoNumber(2);
            lesson2.setCourse(course1);
            lessonRepository.save(lesson2);

            Lesson lesson3 = new Lesson();
            lesson3.setTitle("Variables in Python");
            lesson3.setVideoType(VideoType.YOUTUBE);
            lesson3.setVideoNumber(3);
            lesson3.setCourse(course1);
            lessonRepository.save(lesson3);


            // Enroll 'Test User' into course 'Python 101'
            Enrolls enrollment1 = new Enrolls();
            enrollment1.setEnrolledUser(user1);
            enrollment1.setEnrolledCourse(course1);
            enrollsRepository.save(enrollment1);


            System.out.println("Finished generating data.");
        } else {
            System.out.println("Data generation is disabled.");
        }
    }
}
