package com.cognizant.service;

import com.cognizant.model.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    private final StudentService studentService = new StudentService();

    @Test
    public void testStudentObjectCreation() {
        Student s = new Student("testUser", "test@mail.com", "1234567890", "pass123");
        assertEquals("testUser", s.getUsername());
        assertEquals("test@mail.com", s.getEmail());
    }
}