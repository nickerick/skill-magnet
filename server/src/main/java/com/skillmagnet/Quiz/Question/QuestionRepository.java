package com.skillmagnet.Quiz.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillmagnet.Quiz.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuiz(Quiz q);
}
