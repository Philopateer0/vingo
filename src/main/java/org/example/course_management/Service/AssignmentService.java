package org.example.course_management.Service;

import org.example.course_management.Model.Assignment;
import org.example.course_management.Model.Course;
import org.example.course_management.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentService {

    @Autowired
    private CourseService courseService;

    public int searchForAssignmentInCourse(Course course, Assignment assignment) {
        for (int i = 0; i < course.getAllAssignments().size(); i++) {
            if (course.getAllAssignments().get(i).getTitle().equals(assignment.getTitle())) {
                return i;
            }
        }
        return -1;  
    }

    // Create an assignment
    public Assignment createAssignment(Long courseId, Assignment assignment) {
        Course course = courseService.GetCourse(courseId);
        if (course != null) {
            int index = searchForAssignmentInCourse(course, assignment);
            if (index != -1) {
                return null;
            }
            assignment.setId(course.AssignmentCounter.incrementAndGet());
            course.addAssignment(assignment);
            courseService.UpdateCourse(course);
            return assignment;
        }
        return null;
    }

    public Assignment getAssignment(Long courseId, Long assignmentId) {
        Course course = courseService.GetCourse(courseId);
        if (course != null) {
            for (Assignment assignment : course.getAllAssignments()) {
                if (assignment.getId().equals(assignmentId)) {
                    return assignment;
                }
            }
        }
        return null;
    }

    // List all assignments for a course
    public ArrayList<Assignment> getAssignmentsForCourse(Long courseId) {
        Course course = courseService.GetCourse(courseId);
        if (course != null) {
            return course.getAllAssignments();
        }
        return new ArrayList<>();
    }
    // search for student in course
    public int SearchForStudentInCourse(Long CourseID , Long StudentID) {
        Course course = courseService.GetCourse(CourseID);
        if (course != null) {
            for (int i = 0 ; i < course.GetAllStudents().size() ; i ++) {
                if (course.GetAllStudents().get(i).getId().equals(StudentID)) return i ;
            }
        }
        return -1;
    }

    // Submit an assignment
    public boolean submitAssignment(Long assignmentId, Long courseId, Student student) {
        Course course = courseService.GetCourse(courseId);

        if (SearchForStudentInCourse(course.getId(), student.getId()) == -1) {
            return false; // Student not enrolled
        }

        for (Assignment assignment : course.getAllAssignments()) {
            if (assignment.getId().equals(assignmentId)) {
                if (!assignment.getSubmittedStudents().contains(student)) {
                    assignment.addSubmittedStudent(student);
                    return true;
                }
            }
        }
        return false;
    }


    // Grade an assignment
    public boolean gradeAssignment(Long assignmentId, Long Courseid) {
        Course course = courseService.GetCourse(Courseid);
        for (Assignment assignment : course.getAllAssignments()) {
            if (assignment.getId().equals(assignmentId) && !assignment.isGraded()) {
                assignment.setGraded(true);
                return true;
            }
        }
        return false;
    }

    public String getAssignmentFeedback(Long assignmentId, Long Courseid,Long studentId) {
        Course course = courseService.GetCourse(Courseid);
        for (Assignment assignment : course.getAllAssignments()) {
            if (assignment.getId().equals(assignmentId) && assignment.isGraded()) {
                for (Student student : assignment.getSubmittedStudents()) {
                    if (student.getId().equals(studentId)) {
                        return "Feedback for " + student.getName() + ": " + "Your Assignment has been graded";
                    }
                }
            }
        }
        return "No feedback available.";
    }

}