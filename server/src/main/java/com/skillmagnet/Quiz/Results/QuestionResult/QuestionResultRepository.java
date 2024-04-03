package com.skillmagnet.Quiz.Results.QuestionResult;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillmagnet.Quiz.Question.Question;
import com.skillmagnet.User.User;


public interface QuestionResultRepository extends JpaRepository<QuestionResult, Integer>{
    QuestionResult findByUserAndQuestion(User u, Question q);
}
