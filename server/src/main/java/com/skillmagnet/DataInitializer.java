package com.skillmagnet;

import com.skillmagnet.Course.Course;
import com.skillmagnet.Course.CourseRepository;
import com.skillmagnet.Enrolls.Enrolls;
import com.skillmagnet.Enrolls.EnrollsRepository;
import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.Lesson.LessonRepository;
import com.skillmagnet.Lesson.VideoType;
import com.skillmagnet.Recommendation.CourseNode;
import com.skillmagnet.Recommendation.CourseNodeRepository;
import com.skillmagnet.Recommendation.UserNode;
import com.skillmagnet.Recommendation.UserNodeRepository;
import com.skillmagnet.User.User;
import com.skillmagnet.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

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

    @Autowired
    private UserNodeRepository userNodeRepository;

    @Autowired
    private CourseNodeRepository courseNodeRepository;

    @Value("${data.generation.enabled:false}")
    private boolean dataGenerationEnabled;

    @Override
    public void run(String[] args) {
        if (dataGenerationEnabled) {
            System.out.println("Dropping Graph DB...");
            userNodeRepository.deleteAllNodesAndRelationships();
            System.out.println("Finished Dropping graph ");


            System.out.println("Generating data...");


            // Create Users
            User user1 = new User("Test User", "password");
            userRepository.save(user1);

            try {
                UserNode userNode = new UserNode(user1.getId(), user1.getUsername());
                userNodeRepository.save(userNode);
            } catch (Exception e) {
                System.out.println("GraphDBError: Failed to create User " + user1.getId());
            }

            User user2 = new User("John Doe", "Tigers2!");
            userRepository.save(user2);

            try {
                UserNode userNode = new UserNode(user2.getId(), user2.getUsername());
                userNodeRepository.save(userNode);
            } catch (Exception e) {
                System.out.println("GraphDBError: Failed to create User " + user2.getId());
            }


            // Create Course and Lessons
            Course course1 = new Course();
            course1.setTitle("Python 101");
            course1.setDescription("Learn Python for complete beginners!");
            course1.setCategory("Programming");
            courseRepository.save(course1);

            try {
                CourseNode courseNode = new CourseNode(course1.getId(), course1.getTitle());
                courseNodeRepository.save(courseNode);
            } catch (Exception e) {
                System.out.println("GraphDBError: Failed to create Course " + course1.getId());
            }

            Lesson lesson1 = new Lesson();
            lesson1.setTitle("Overview");
            lesson1.setVideoType(VideoType.SELF_HOSTED);
            lesson1.setVideoNumber(1);
            lesson1.setCourse(course1);
            lessonRepository.save(lesson1);

            Lesson lesson2 = new Lesson();
            lesson2.setTitle("Hello, World");
            lesson2.setVideoType(VideoType.YOUTUBE);
            lesson2.setVideoNumber(2);
            lesson2.setCourse(course1);
            lessonRepository.save(lesson2);


            // Enroll 'Test User' into course 'Python 101'
            Enrolls enrollment1 = new Enrolls();
            enrollment1.setEnrolledUser(user1);
            enrollment1.setEnrolledCourse(course1);
            enrollsRepository.save(enrollment1);

            try {
                userNodeRepository.enrollUserInCourse(user1.getId(), course1.getId());
            } catch (Exception e) {
                System.out.println("GraphDBError: Failed to create enrollment between User " + user1.getId() +
                        " and Course " + course1.getId());
            }


            // RECOMMENDATIONS DATA POPULATION
            // Create multiple courses
            List<Integer> courseIds = new LinkedList<>();
            for (int i = 2; i < 6; i++) {
                Course newCourse = new Course();
                newCourse.setTitle("Test Course " + i);
                newCourse.setDescription("Bulk testing course to test recommendations fr");
                newCourse.setCategory("Gains");
                courseRepository.save(newCourse);

                try {
                    CourseNode courseNode = new CourseNode(newCourse.getId(), newCourse.getTitle());
                    courseNodeRepository.save(courseNode);
                } catch (Exception e) {
                    System.out.println("GraphDBError: Failed to create Course " + newCourse.getId());
                }

                courseIds.add(newCourse.getId());
            }

            // Create multiple users
            List<Integer> userIds = new LinkedList<>();
            for (int i = 2; i < 6; i++) {
                User newUser = new User("Bulk User " + i, "password");
                userRepository.save(newUser);

                try {
                    UserNode userNode = new UserNode(newUser.getId(), newUser.getUsername());
                    userNodeRepository.save(userNode);
                } catch (Exception e) {
                    System.out.println("GraphDBError: Failed to create User " + newUser.getId());
                }

                userIds.add(newUser.getId());
            }

            // Enroll 2 users into 3 new courses
            for (int i = 0; i < 2; i++) {
                User user = userRepository.findById(userIds.get(i));
                for (int j = 0; j < 3; j++) {
                    Course course = courseRepository.findById(courseIds.get(j));

                    Enrolls newEnroll = new Enrolls();
                    newEnroll.setEnrolledUser(user);
                    newEnroll.setEnrolledCourse(course);
                    enrollsRepository.save(newEnroll);

                    try {
                        userNodeRepository.enrollUserInCourse(user.getId(), course.getId());
                    } catch (Exception e) {
                        System.out.println("GraphDBError: Failed to create enrollment between User " + user.getId() +
                                " and Course " + course.getId());
                    }
                }
            }

            // Enroll 1 new user into course 1
            User user = userRepository.findById(userIds.get(3));
            Course course = courseRepository.findById(courseIds.getFirst());

            Enrolls newEnroll = new Enrolls();
            newEnroll.setEnrolledUser(user);
            newEnroll.setEnrolledCourse(course);
            enrollsRepository.save(newEnroll);

            try {
                userNodeRepository.enrollUserInCourse(user.getId(), course.getId());
            } catch (Exception e) {
                System.out.println("GraphDBError: Failed to create enrollment between User " + user.getId() +
                        " and Course " + course.getId());
            }

            // Enroll very first user into new course 2
            course = courseRepository.findById(courseIds.get(1));

            newEnroll = new Enrolls();
            newEnroll.setEnrolledUser(user1);
            newEnroll.setEnrolledCourse(course);
            enrollsRepository.save(newEnroll);

            try {
                userNodeRepository.enrollUserInCourse(user1.getId(), course.getId());
            } catch (Exception e) {
                System.out.println("GraphDBError: Failed to create enrollment between User " + user1.getId() +
                        " and Course " + course.getId());
            }


            System.out.println("Finished generating data.");
        } else {
            System.out.println("Data generation is disabled.");
        }
    }
}
