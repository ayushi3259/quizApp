package com.quizapp.quizapp.service;

import com.quizapp.quizapp.Question;
import com.quizapp.quizapp.Quiz;
import com.quizapp.quizapp.dao.QuestionDao;
import com.quizapp.quizapp.dao.QuizDao;
import com.quizapp.quizapp.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<?> createQuiz(String category, int numQuest, String title) {
        List<Question> questions= questionDao.findQuestionRandom(numQuest,category);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuest(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionFromDb=quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser= new ArrayList<>();
        for(Question q: questionFromDb){
            QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getCategory(),q.getDifficultylevel(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser, HttpStatusCode.valueOf(200));
    }
}
