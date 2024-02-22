package com.skillmagnet.Lesson;

import com.skillmagnet.Course.Course;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LessonRequest {

    @NotEmpty
    private String title;

    @Positive(message = "Must supply a valid course id")
    private int courseId;

    @NotEmpty
    private String videoLink;

    @NotNull(message = "Must supply either SELF_HOSTED or YOUTUBE")
    private VideoType videoType;

    @Positive(message = "Must supply a valid lesson number")
    private int videoNumber;

    Lesson toLesson(Course course) {
        return Lesson.builder()
                .title(this.title)
                .course(course)
                .videoLink(this.videoLink)
                .videoType(this.videoType)
                .videoNumber(this.videoNumber)
                .build();
    }
}
