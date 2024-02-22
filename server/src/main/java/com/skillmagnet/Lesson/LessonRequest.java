package com.skillmagnet.Lesson;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
//import org.springframework.lang.NonNull;

@Getter
@Data
public class LessonRequest {

    @NotEmpty
    private String title;

    @NotNull
    private int courseId;

    @NotEmpty
    private String videoLink;

    @NotNull(message = "Must supply either SELF_HOSTED or YOUTUBE")
    private VideoType videoType;

    @NotNull
    private int videoNumber;

}
