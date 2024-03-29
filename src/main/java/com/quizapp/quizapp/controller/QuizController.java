package com.quizapp.quizapp.controller;

import com.quizapp.quizapp.model.QuestionWrapper;
import com.quizapp.quizapp.model.Response;
import com.quizapp.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam String category, @RequestParam int numQuest,@RequestParam String title){
        return quizService.createQuiz(category,numQuest,title);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuizQuest(id);

    }

    @PostMapping("/submit/{id}/")
    public ResponseEntity<Integer> submitQuestion(@PathVariable Integer id, @RequestBody List<Response> responses){
        int rightAnswer= quizService.submitQues(id,responses);
        return new ResponseEntity<>(rightAnswer,HttpStatus.OK);
    }

    }
