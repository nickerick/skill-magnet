package com.skillmagnet.Enrolls;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class EnrollsLessonRequest {
    @Positive(message = "must supply valid lesson id")
    private int lessonId;

    @Positive(message = "must supply valid user id")
    private int userId;
}
