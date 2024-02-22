package com.skillmagnet.Lesson;

import com.skillmagnet.Course.Course;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;

    private String videoLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "video_type")
    private VideoType videoType;

    private int videoNumber;

}
