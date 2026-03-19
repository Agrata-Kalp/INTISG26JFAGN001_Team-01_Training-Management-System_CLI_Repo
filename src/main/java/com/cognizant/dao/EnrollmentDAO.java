package com.cognizant.dao;

import com.cognizant.model.Enrollment;
import java.sql.SQLException;
import java.util.List;

public interface EnrollmentDAO {
    boolean enrollStudent(Enrollment enrollment) throws SQLException;
    boolean isAlreadyEnrolled(int studentId, int courseId) throws SQLException;
    List<Enrollment> getStudentEnrollments(int studentId) throws SQLException;
}