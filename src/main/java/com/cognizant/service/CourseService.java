package com.cognizant.service;

import com.cognizant.daoImpl.CourseDAOImpl;
import com.cognizant.model.Course;
import java.sql.SQLException;
import java.util.List;

public class CourseService {
    private final CourseDAOImpl courseDAO = new CourseDAOImpl();

    public boolean createCourse(String name, String desc, int trainerId) throws SQLException {
        Course course = new Course(name, desc, trainerId);
        return courseDAO.addCourse(course);
    }

    public List<Course> listAllAvailableCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }
}