package com.cognizant.service;

import com.cognizant.model.Course;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    @Test
    public void testCourseDataIntegrity() {
        Course c = new Course("Java 101", "Basic Java", 1);
        assertNotNull(c.getCourseName());
        assertEquals(1, c.getTrainerId());
    }
}