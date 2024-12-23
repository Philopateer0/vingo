package com.example.demo.controllers;

import com.example.demo.models.Quiz;
import com.example.demo.models.Student;
import com.example.demo.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/GetCourse/{id}/CreateQuiz")
    public Quiz CreateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        return  quizService.createQuiz(id, quiz);
    }

    @GetMapping("/GetCourse/{id}/getQuizsForCourse")
    public ArrayList<Quiz> getQuizsForCourse(@PathVariable Long id) {
        return quizService.getQuizsForCourse(id);
    }

    @GetMapping("/GetCourse/{id}/getQuiz/{QuizId}")
    public Quiz getQuiz(@PathVariable Long id, @PathVariable Long QuizId) {
        return quizService.getQuiz(id,QuizId);
    }

    @PostMapping("/GetCourse/{id}/getQuiz/{QuizId}/submitQuiz")
    public String submitQuiz(@PathVariable Long QuizId, @PathVariable Long id,@RequestBody Student student ) {
        if (quizService.submitQuiz(QuizId,id,student)) return "Quiz Submitted";
        return "Quiz Not Submitted";
    }

    @GetMapping("/GetCourse/{CourseID}/GetQuiz/{QuizID}/getQuizSubmitters")
    public List<Student> getQuizSubmitters(@PathVariable Long CourseID, @PathVariable Long QuizID) {
        return  quizService.getQuizSubmitters(CourseID,QuizID);
    }

    @PostMapping("/GetCourse/{id}/GetQuiz/{QuizId}/gradeQuiz")
    public String gradeQuiz(@PathVariable Long QuizId, @PathVariable Long id) {
        if(quizService.gradeQuiz(QuizId, id)) return "Quiz Graded";
        return "Quiz Not Graded";
    }

    @GetMapping("/GetCourse/{id}/getQuiz/{QuizId}/getQuizFeedback")
    public String getQuizFeedback(@PathVariable Long QuizId, @PathVariable Long id) {
        return quizService.getQuizFeedback(QuizId, id);
    }
    
    @GetMapping("/GetCourse/{id}/getQuiz/{QuizId}/getQuizScores")
    public List<String> getQuizScores(@PathVariable Long id, @PathVariable Long QuizId) {
        return quizService.getQuizScores(id,QuizId);
    }
}