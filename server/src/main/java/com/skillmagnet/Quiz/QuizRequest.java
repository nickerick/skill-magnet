package com.skillmagnet.Quiz;

import com.skillmagnet.Lesson.Lesson;

import lombok.Builder;
import lombok.Getter;

/**
 * Used in quiz controller to create relation between newly created
 * quiz and lesson it belongs to
 */
@Getter
@Builder
public class QuizRequest {

    private String title;
    private String description;
    private int lessonId;

    Quiz toQuiz(Lesson lesson) {
        return Quiz.builder()
        .title(title)
        .description(description)
        .lesson(lesson)
        .build();
    }
}
