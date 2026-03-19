package com.cognizant.dao;

import com.cognizant.model.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    boolean addCourse(Course course) throws SQLException;
    List<Course> getAllCourses() throws SQLException;
    List<Course> getCoursesByTrainer(int trainerId) throws SQLException;
    boolean softDeleteCourse(int courseId) throws SQLException;
}