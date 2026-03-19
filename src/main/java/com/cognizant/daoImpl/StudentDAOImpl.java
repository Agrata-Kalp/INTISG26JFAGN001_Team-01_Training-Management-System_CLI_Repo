package com.cognizant.daoImpl;

import com.cognizant.config.DatabaseConfig;
import com.cognizant.dao.StudentDAO;
import com.cognizant.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean registerStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (username, email, mobile_number, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, student.getUsername());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getMobileNumber());
            pstmt.setString(4, student.getPassword());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public Student loginStudent(String username, String password) throws SQLException {
        String query = "SELECT * FROM students WHERE username = ? AND password = ? AND is_deleted = false";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setUsername(rs.getString("username"));
                student.setEmail(rs.getString("email"));
                return student;
            }
        }
        return null; // Login failed
    }

    @Override
    public boolean softDeleteStudent(int id) throws SQLException {
        String query = "UPDATE students SET is_deleted = true WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT id, username, email, mobile_number FROM students WHERE is_deleted = false";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setEmail(rs.getString("email"));
                s.setMobileNumber(rs.getString("mobile_number"));
                students.add(s);
            }
        }
        return students;
    }
}