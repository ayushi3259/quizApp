package com.quizapp.quizapp.dao;

import com.quizapp.quizapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    List<Question> findByCategoryAndDifficultylevel(String category, String difficultylevel);

    @Query(value="SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQuest",nativeQuery = true)
    List<Question> findQuestionRandom(int numQuest, String category);

}
