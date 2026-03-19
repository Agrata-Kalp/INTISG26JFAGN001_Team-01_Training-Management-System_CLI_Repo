package com.cognizant.daoImpl;

import com.cognizant.config.DatabaseConfig;
import com.cognizant.dao.CourseDAO;
import com.cognizant.model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public boolean addCourse(Course course) throws SQLException {
        String query = "INSERT INTO courses (course_name, description, trainer_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getDescription());
            pstmt.setInt(3, course.getTrainerId());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses WHERE is_deleted = false";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setCourseName(rs.getString("course_name"));
                c.setDescription(rs.getString("description"));
                c.setTrainerId(rs.getInt("trainer_id"));
                courses.add(c);
            }
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesByTrainer(int trainerId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses WHERE trainer_id = ? AND is_deleted = false";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, trainerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setCourseName(rs.getString("course_name"));
                courses.add(c);
            }
        }
        return courses;
    }

    @Override
    public boolean softDeleteCourse(int courseId) throws SQLException {
        String query = "UPDATE courses SET is_deleted = true WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, courseId);
            return pstmt.executeUpdate() > 0;
        }
    }
}