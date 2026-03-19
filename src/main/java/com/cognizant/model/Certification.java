package com.cognizant.model;

import java.sql.Timestamp;

public class Certification {
    private int id;
    private int studentId;
    private int courseId;
    private Timestamp issueDate;
    private String certificateUrl; // Could be a generated ID or string

    public Certification() {}

    public Certification(int studentId, int courseId, String certificateUrl) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.certificateUrl = certificateUrl;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getCertificateUrl() { return certificateUrl; }
    public void setCertificateUrl(String certificateUrl) { this.certificateUrl = certificateUrl; }
}