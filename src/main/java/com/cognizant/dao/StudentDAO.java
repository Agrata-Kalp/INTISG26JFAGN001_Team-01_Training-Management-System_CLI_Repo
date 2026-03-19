package com.cognizant.dao;

import com.cognizant.model.Student;
import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    boolean registerStudent(Student student) throws SQLException;
    Student loginStudent(String username, String password) throws SQLException;
    boolean softDeleteStudent(int id) throws SQLException;
    List<Student> getAllStudents() throws SQLException;
}