package com.skillmagnet;

import com.skillmagnet.Course.Course;
import com.skillmagnet.Course.CourseRepository;
import com.skillmagnet.Enrolls.Enrolls;
import com.skillmagnet.Enrolls.EnrollsRepository;
import com.skillmagnet.Lesson.Lesson;
import com.skillmagnet.Lesson.LessonRepository;
import com.skillmagnet.Lesson.VideoType;
import com.skillmagnet.Quiz.Quiz;
import com.skillmagnet.Quiz.QuizRepository;
import com.skillmagnet.Quiz.Question.Question;
import com.skillmagnet.Quiz.Question.QuestionRepository;
import com.skillmagnet.Quiz.Question.QuestionType;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOption;
import com.skillmagnet.Quiz.Question.QuestionOption.QuestionOptionRepository;
import com.skillmagnet.Quiz.Results.GradingService;
import com.skillmagnet.Quiz.Results.UserSubmission;
import com.skillmagnet.Quiz.Results.UserSubmission.Answer;
import com.skillmagnet.User.User;
import com.skillmagnet.User.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private GradingService gradingService;

    @Autowired
    QuestionOptionRepository questionOptionRepository;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

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

            Quiz quiz = new Quiz();
            quiz.setTitle("Overview Quiz");
            quiz.setDescription("Check Your Understanding - Overview");
            quiz.setLesson(lesson1);
            quizRepository.save(quiz);

            Question question1 = new Question();
            question1.setQuiz(quiz);
            question1.setQuestionType(QuestionType.SA);
            question1.setQuestionText("What language will we be studying?");
            question1.setCorrectShortAnswer("python");
            question1.setOptions(null);
            questionRepository.save(question1);
            
            Question question2 = new Question();
            question2.setQuiz(quiz);
            question2.setQuestionType(QuestionType.MCQ);
            question2.setQuestionText("What year was python invented?");
            question2.setCorrectShortAnswer("N/A");
            questionRepository.save(question2);

            // Creating options for question2
            QuestionOption qo1 = new QuestionOption();
            qo1.setOptionText("2001");
            qo1.setIsCorrect(false);
            qo1.setQuestion(question2);
            questionOptionRepository.save(qo1);

            QuestionOption qo2 = new QuestionOption();
            qo2.setOptionText("1990");
            qo2.setIsCorrect(false);
            qo2.setQuestion(question2);
            questionOptionRepository.save(qo2);

            QuestionOption qo3 = new QuestionOption();
            qo3.setOptionText("1998");
            qo3.setIsCorrect(false);
            qo3.setQuestion(question2);
            questionOptionRepository.save(qo3);

            QuestionOption qo4 = new QuestionOption();
            qo4.setOptionText("1991");
            qo4.setIsCorrect(true);
            qo4.setQuestion(question2);
            questionOptionRepository.save(qo4);
      
            List<Answer> answers = new ArrayList<Answer>(Arrays.asList(
                new Answer(question1.getQuestionId(), "python", null),
                new Answer(question2.getQuestionId(), null, qo4.getOptionId())
            ));


            UserSubmission submission = new UserSubmission();
            submission.setQuizId(quiz.getQuizId());
            submission.setUserId(user1.getId());
            submission.setAnswers(answers);

            // Generates rows in QuestionResults Table
            gradingService.gradeSubmission(submission);

            //Second quiz object

            Quiz quiz2 = new Quiz();
            quiz2.setTitle("Overview Quiz");
            quiz2.setDescription("Check Your Understanding - Hello, World");
            quiz2.setLesson(lesson2);
            quizRepository.save(quiz2);

            Question question3 = new Question();
            question3.setQuiz(quiz2);
            question3.setQuestionType(QuestionType.SA);
            question3.setQuestionText("What is the command to show text in the console?");
            question3.setCorrectShortAnswer("print");
            question3.setOptions(null);
            questionRepository.save(question3);
            
            Question question4 = new Question();
            question4.setQuiz(quiz2);
            question4.setQuestionType(QuestionType.MCQ);
            question4.setQuestionText("What will this print? print(2 + 2)");
            question4.setCorrectShortAnswer("N/A");
            questionRepository.save(question4);

            // Creating options for question2
            QuestionOption qo5 = new QuestionOption();
            qo5.setOptionText("22");
            qo5.setIsCorrect(false);
            qo5.setQuestion(question4);
            questionOptionRepository.save(qo5);

            QuestionOption qo6 = new QuestionOption();
            qo6.setOptionText("2 + 2");
            qo6.setIsCorrect(false);
            qo6.setQuestion(question4);
            questionOptionRepository.save(qo6);

            QuestionOption qo7 = new QuestionOption();
            qo7.setOptionText("=4");
            qo7.setIsCorrect(false);
            qo7.setQuestion(question4);
            questionOptionRepository.save(qo7);

            QuestionOption qo8 = new QuestionOption();
            qo8.setOptionText("4");
            qo8.setIsCorrect(true);
            qo8.setQuestion(question4);
            questionOptionRepository.save(qo8);
      
            List<Answer> answers2 = new ArrayList<Answer>(Arrays.asList(
                new Answer(question3.getQuestionId(), "print", null),
                new Answer(question4.getQuestionId(), null, qo8.getOptionId())
            ));


            UserSubmission submission2 = new UserSubmission();
            submission2.setQuizId(quiz2.getQuizId());
            submission2.setUserId(user2.getId());
            submission2.setAnswers(answers2);

            // Generates rows in QuestionResults Table
            gradingService.gradeSubmission(submission2);

            System.out.println("Finished generating data.");
        } else {
            System.out.println("Data generation is disabled.");
        }
    }
}
