package com.quizapp.quizapp.controller;


import com.quizapp.quizapp.Question;
import com.quizapp.quizapp.service.QuestionService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<?> getAllQuestions(){
        List<Question> getQuestions = questionService.getAllQuestions();
        return new ResponseEntity<>(getQuestions, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getQuestionsByCategory(@PathVariable String category){
        List<Question> getQuestionByCat = questionService.getQuestionsByCategory(category);
        return new ResponseEntity<>(getQuestionByCat,HttpStatus.OK);
    }

    @GetMapping("category/{category}/{difficultylevel}")
    public ResponseEntity<?> getQuestionsByCategoryAndDiff(@PathVariable String category, @PathVariable String difficultylevel){
        List<Question> getQuestionByCatAndDiff = questionService.getQuestionsByCategoryAndDiff(category,difficultylevel);
        return new ResponseEntity<>(getQuestionByCatAndDiff,HttpStatus.OK);
    }

    @PostMapping("addQuestions")
    public ResponseEntity<?> addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

}
