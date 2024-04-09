package com.skillmagnet.Quiz;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillmagnet.Lesson.Lesson;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Optional<List<Quiz>> findByLesson(Lesson l);
}

