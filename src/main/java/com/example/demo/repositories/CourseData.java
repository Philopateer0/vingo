package com.example.demo.repositories;

import java.util.ArrayList;

import com.example.demo.models.Course;
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
