package com.quizapp.quizapp.service;

import com.quizapp.quizapp.Question;
import com.quizapp.quizapp.Quiz;
import com.quizapp.quizapp.dao.QuestionDao;
import com.quizapp.quizapp.dao.QuizDao;
import com.quizapp.quizapp.model.QuestionWrapper;
import com.quizapp.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<?> createQuiz(String category, int numQuest, String title) {
        List<Question> questions= questionDao.findQuestionRandom(numQuest,category);
        if(questions==null || questions.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no question found");
        }
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuest(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        if(quiz.isPresent()) {
            List<Question> questionFromDb = quiz.get().getQuestions();
            List<QuestionWrapper> questionForUser = new ArrayList<>();
            for (Question q : questionFromDb) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getCategory(), q.getDifficultylevel(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionForUser.add(qw);
            }
            return new ResponseEntity<>(questionForUser, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public int submitQues(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        Map<Integer,Question> mapQuestion=new HashMap<>();
        for(Question q: questions){
            mapQuestion.put(q.getId(),q);
        }
        int right=0;
        for(Response response: responses){
            Integer questionId = response.getId();
            Question question=mapQuestion.get(questionId);
            if(question!=null && response.getResponse().equals(question.getRightanswer())) {
                right++;
            }

            }
        return right;
    }
}
