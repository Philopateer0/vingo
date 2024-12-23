package org.example.course_management.Controller;

import org.example.course_management.Model.Quiz;
import org.example.course_management.Model.Course;
import org.example.course_management.Model.Student;
import org.example.course_management.Service.QuizService;
import org.example.course_management.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class QuizController {

    @Autowired
    private QuizService quizService;

    // create quiz
    @PostMapping("/GetCourse/{id}/CreateQuiz")
    public Quiz CreateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        return  quizService.createQuiz(id, quiz);

    }

    // List all Quizs for a course
    @GetMapping("/GetCourse/{id}/getQuizsForCourse")
    public ArrayList<Quiz> getQuizsForCourse(@PathVariable Long id) {
        return quizService.getQuizsForCourse(id);

    }
    @GetMapping("/GetCourse/{id}/getQuiz/{QuizId}")
    public Quiz getQuiz(@PathVariable Long id, @PathVariable Long QuizId) {
        return quizService.getQuiz(id,QuizId);
    }



    // Submit an Quiz (Student)
    @PostMapping("/GetCourse/{id}/getQuiz/{QuizId}/submitQuiz")
    public String submitQuiz(@PathVariable Long QuizId, @PathVariable Long id,@RequestBody Student student ) {

        if (quizService.submitQuiz(QuizId,id,student))
        {
            return "Quiz Submitted";
        }
        return "Quiz Not Submitted";
    }
    // get all submitter
        @GetMapping("/GetCourse/{CourseID}/GetQuiz/{QuizID}/getQuizSubmitters")
    public List<Student> getQuizSubmitters(@PathVariable Long CourseID, @PathVariable Long QuizID) {
        return  quizService.getQuizSubmitters(CourseID,QuizID);
    }

    // Grade an Quiz (Instructor)
        @PostMapping("/GetCourse/{id}/GetQuiz/{QuizId}/gradeQuiz")
    public String gradeQuiz(@PathVariable Long QuizId, @PathVariable Long id) {
        if(quizService.gradeQuiz(QuizId, id)){
            return "Quiz Graded";
        }
        return "Quiz Not Graded";
    }

    // Get feedback for an Quiz (Student)
    @GetMapping("/GetCourse/{id}/getQuiz/{QuizId}/getQuizFeedback")
    public String getQuizFeedback(@PathVariable Long QuizId, @PathVariable Long id) {
        return quizService.getQuizFeedback(QuizId,id);
    }

}
