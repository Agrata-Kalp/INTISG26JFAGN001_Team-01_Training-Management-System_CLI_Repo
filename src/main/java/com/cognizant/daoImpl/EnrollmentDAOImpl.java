package com.cognizant.daoImpl;

import com.cognizant.config.DatabaseConfig;
import com.cognizant.dao.EnrollmentDAO;
import com.cognizant.model.Enrollment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Override
    public boolean enrollStudent(Enrollment enrollment) throws SQLException {
        String query = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, enrollment.getStudentId());
            pstmt.setInt(2, enrollment.getCourseId());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean isAlreadyEnrolled(int studentId, int courseId) throws SQLException {
        String query = "SELECT COUNT(*) FROM enrollments WHERE student_id = ? AND course_id = ? AND is_deleted = false";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        }
        return false;
    }

    @Override
    public List<Enrollment> getStudentEnrollments(int studentId) throws SQLException {
        List<Enrollment> list = new ArrayList<>();
        String query = "SELECT * FROM enrollments WHERE student_id = ? AND is_deleted = false";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setCourseId(rs.getInt("course_id"));
                e.setStatus(rs.getString("status"));
                list.add(e);
            }
        }
        return list;
    }
}