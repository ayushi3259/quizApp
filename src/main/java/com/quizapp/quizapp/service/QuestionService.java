package com.quizapp.quizapp.service;

import com.quizapp.quizapp.Question;
import com.quizapp.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    
    public List<Question> getAllQuestions() {

        return questionDao.findAll();

    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public List<Question> getQuestionsByCategoryAndDiff(String category, String difficultylevel) {
        return questionDao.findByCategoryAndDifficultylevel(category,difficultylevel);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }
}
