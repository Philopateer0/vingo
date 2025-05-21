package com.example.demo.controllers;

import com.example.demo.models.Assignment;
import com.example.demo.models.Student;
import com.example.demo.services.AssignmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('INSTRUCTOR')")
    @PostMapping("/GetCourse/{id}/CreateAssignment")
    public Assignment CreateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        return assignmentService.createAssignment(id, assignment);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('INSTRUCTOR')")
    @GetMapping("/GetCourse/{id}/getAssignmentsForCourse")
    public List<Assignment> getAssignmentsForCourse(@PathVariable Long id) {
        return assignmentService.getAssignmentsForCourse(id);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('INSTRUCTOR')")
    @GetMapping("/GetCourse/{id}/getAssignment/{AssignmentId}")
    public Assignment getAssignment(@PathVariable Long id, @PathVariable Long AssignmentId) {
        return assignmentService.getAssignment(id, AssignmentId);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/GetCourse/{id}/getAssignment/{AssignmentId}/submitAssignment")
    public String submitAssignment(@PathVariable Long AssignmentId, @PathVariable Long id, @RequestBody Student student) {
        if (assignmentService.submitAssignment(AssignmentId, id, student)) return "Assignment Submitted";
        return "Assignment Not Submitted";
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('INSTRUCTOR')")
    @PostMapping("/GetCourse/{id}/getAssignment/{AssignmentId}/gradeAssignment")
    public String gradeAssignment(@PathVariable Long AssignmentId, @PathVariable Long id) {
        if (assignmentService.gradeAssignment(AssignmentId, id)) return "Assignment Graded";
        return "Assignment Not Graded";
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/GetCourse/{id}/getAssignment/{AssignmentId}/getAssignmentFeedback/{StudentId}")
    public String getAssignmentFeedback(@PathVariable Long AssignmentId, @PathVariable Long id, @PathVariable Long StudentId) {
        return assignmentService.getAssignmentFeedback(AssignmentId, id, StudentId);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('INSTRUCTOR')")
    @GetMapping("/GetCourse/{id}/getAssignment/{AssignmentId}/getAssignmentSubmitters")
    public List<Student> getAssignmentSubmitters(@PathVariable Long id, @PathVariable Long AssignmentId) {
        return assignmentService.getAssignemntSubmitters(id, AssignmentId);
    }
}