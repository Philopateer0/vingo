package org.example.course_management.Data;

import java.util.ArrayList;

import org.example.course_management.Model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseData {
    private ArrayList<Course> AllCourses = new ArrayList<>() ;
    
    public Course GetCourseByIndex(int index) {
        return AllCourses.get(index) ;
    }

    public ArrayList<Course> GetAllCourses() {
        return AllCourses ;
    }

    public void DeleteCourse(int index) {
        AllCourses.remove(index) ;
    }

    public void AddCourse(Course course) {
        AllCourses.add(course) ;
    }

    public void UpdateCourse(Course course , int index) {
        AllCourses.set(index, course) ;
    }

}
