package org.example.course_management.Service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.example.course_management.Data.CourseData;
import org.example.course_management.Model.Constance;
import org.example.course_management.Model.Course;
import org.example.course_management.Model.Lesson;
import org.example.course_management.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class CourseService {

    @JsonIgnore
    AtomicLong IDCounter = new AtomicLong(0) ; 

    @Autowired
    CourseData courseData ;

    //! ---------------------------- Course Services --------------------------------

    public ArrayList<Course> GetAllCourses() {
        return courseData.GetAllCourses() ;
    }

    public boolean AddCourse(Course course) {
        int index = SearchForCourseByName(course) ;
        if (index != Constance.No_Match) return false ;
        course.setId(IDCounter.incrementAndGet());
        courseData.AddCourse(course) ;
        return true ;
    }

    public void DeleteCourse(Long id) {
        Course course = GetCourse(id) ;
        if (course != null) courseData.DeleteCourse(SearchForCourseByName(course)) ;
    }

    public boolean UpdateCourse(Course course) {
        int index = SearchForCourseByID(course) ;
        if (index == Constance.No_Match) return false ;
        courseData.UpdateCourse(course, index) ;
        return true ;
    }

    public int SearchForCourseByID(Course course) {
        for (int i = 0 ; i < GetAllCourses().size() ; i ++) {
            if (courseData.GetCourseByIndex(i).getId().equals(course.getId())) return i ;  
        }
        return Constance.No_Match ;
    }

    public int SearchForCourseByName(Course course) {
        for (int i = 0 ; i < GetAllCourses().size() ; i ++) {
            if (courseData.GetCourseByIndex(i).getName().equals(course.getName())) return i ;  
        }
        return Constance.No_Match ;
    }

    public Course GetCourse(Long ID) {
        for (int i = 0 ; i < GetAllCourses().size() ; i ++) {
            if (courseData.GetCourseByIndex(i).getId().equals(ID)) return courseData.GetCourseByIndex(i) ;
        }
        return null ;
    }

    //! ---------------------------- Lesson Services --------------------------------

    public int SearchForLessonInCourse(Course course , Lesson lesson) {
        for (int i = 0 ; i < course.GetAllLessons().size() ; i ++) {
            if (course.GetAllLessons().get(i).getTitle().equals(lesson.getTitle())) return i ;
        }
        return Constance.No_Match ;
    }

    public boolean AddLesson(Long id , Lesson lesson) {
        Course course = GetCourse(id) ;
        if (course != null) {
            int index = SearchForLessonInCourse(course, lesson) ;
            if (index == Constance.No_Match) {
                lesson.setId(course.LessonCounter.incrementAndGet());
                course.AddLesson(lesson);
                courseData.UpdateCourse(course, SearchForCourseByID(course));
                return true ;
            }
        }
        return false ;
    }

    public boolean UpdateLesson(Long id , Lesson lesson) {
        Course course = GetCourse(id) ;
        if (course != null) {
            int index = SearchForLessonInCourse(course, lesson) ;
            if (index != Constance.No_Match) {
                course.UpdateLesson(lesson, index);
                courseData.UpdateCourse(course, SearchForCourseByID(course));
                return true ;
            }
        }
        return false ;
    }

    public Lesson GetLesson(Long CourseID , Long LessonID) {
        Course course = GetCourse(CourseID) ;
        if (course != null) {
            for (int i = 0 ; i < course.GetAllLessons().size() ; i ++) {
                if (course.GetAllLessons().get(i).getId().equals(LessonID)) return course.GetAllLessons().get(i) ;
            }
        }
        return null ;
    }

    public boolean DeleteLesson(Long CourseID , Long LessonID) {
        Course course = GetCourse(CourseID) ;
        if (course != null) {
            for (int i = 0 ; i < course.GetAllLessons().size() ; i ++) {
                if (course.GetAllLessons().get(i).getId().equals(LessonID)) {
                    course.GetAllLessons().remove(i) ;
                    return true ;
                }
            }
        }
        return false ;
    }

    //! ---------------------------- Student Services --------------------------------

    public int SearchForStudentInCourse(Long CourseID , Long StudentID) {
        Course course = GetCourse(StudentID);
        if (course != null) {
            for (int i = 0 ; i < course.GetAllStudents().size() ; i ++) {
                if (course.GetAllStudents().get(i).getId().equals(StudentID)) return i ;
            }
        }
        return Constance.No_Match ;
    }

    public boolean EnrollStudentInCourse(Long CourseID , Student student) {
        Course course = GetCourse(CourseID) ;
        if (course != null) {
            int index = SearchForStudentInCourse(CourseID, student.getId()) ;
            if (index == Constance.No_Match) {
                course.EnrollStudent(student);
                courseData.UpdateCourse(course, SearchForCourseByID(course));
                return true ;
            }
        }
        return false ;
    }

    public void DeleteStudentFromCourse(Long CourseID , Long StudentID) {
        Course course = GetCourse(CourseID) ;
        if (course != null) {
            for (int i = 0 ; i < course.GetAllStudents().size() ; i ++) {
                if (course.GetAllStudents().get(i).getId().equals(StudentID)) {
                    course.GetAllStudents().remove(SearchForStudentInCourse(CourseID, StudentID)) ;
                }
            }
        }
    }

    public ArrayList<Student> GetAllStudentsOfCourse(Long CourseID) {
        return GetCourse(CourseID).GetAllStudents() ;
    }

    //! ---------------------------- Attendance Services --------------------------------

    public Long GenerateOTPForLesson(Long CourseID , Long LessonID) {
        Lesson lesson = GetLesson(CourseID, LessonID) ;
        if (lesson != null) {
            return lesson.GenerateOTP() ;
        }
        return (long) Constance.No_Match ;
    }

    public boolean MarkStudentInAttendance(Long CourseID , Long LessonID , Student student , Long OTP) {
        Lesson lesson = GetLesson(CourseID, LessonID) ;
        if (lesson != null) {
            if (OTP.equals(lesson.GetCurrentOTP())) {
                lesson.GetAttendanceList().add(student) ;
                return true ;
            }
        }
        return false ;
    }

    public ArrayList<Student> ViewAttendanceList(Long CourseID , Long LessonID) {
        return GetLesson(CourseID, LessonID).GetAttendanceList() ;
    }

}
