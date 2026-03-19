package com.cognizant.service;

import com.cognizant.daoImpl.StudentDAOImpl;
import com.cognizant.exception.UserAlreadyExistsException;
import com.cognizant.model.Student;
import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentDAOImpl studentDAO = new StudentDAOImpl();

    public boolean registerStudent(Student student) throws UserAlreadyExistsException, SQLException {
        try {
            return studentDAO.registerStudent(student);
        } catch (SQLException e) {
            // MySQL Error Code 1062 is for "Duplicate Entry"
            if (e.getErrorCode() == 1062) {
                throw new UserAlreadyExistsException("Error: Username, Email, or Mobile Number already registered!");
            }
            throw e; // Rethrow if it's a different SQL error
        }
    }

    public Student login(String username, String password) throws SQLException {
        return studentDAO.loginStudent(username, password);
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }
}