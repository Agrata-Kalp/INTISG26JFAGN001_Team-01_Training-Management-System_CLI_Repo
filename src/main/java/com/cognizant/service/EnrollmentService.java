package com.cognizant.service;

import com.cognizant.daoImpl.EnrollmentDAOImpl;
import com.cognizant.model.Enrollment;
import java.sql.SQLException;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentDAOImpl enrollmentDAO = new EnrollmentDAOImpl();

    public String enrollInCourse(int studentId, int courseId) throws SQLException {
        if (enrollmentDAO.isAlreadyEnrolled(studentId, courseId)) {
            return "You are already enrolled in this course!";
        }

        Enrollment enrollment = new Enrollment(studentId, courseId);
        if (enrollmentDAO.enrollStudent(enrollment)) {
            return "Successfully enrolled in course ID: " + courseId;
        }
        return "Enrollment failed. Please try again.";
    }

    public List<Enrollment> getStudentEnrollments(int studentId) throws SQLException {
        return enrollmentDAO.getStudentEnrollments(studentId);
    }
}