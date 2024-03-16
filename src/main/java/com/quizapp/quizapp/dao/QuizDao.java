package com.quizapp.quizapp.dao;

import com.quizapp.quizapp.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
