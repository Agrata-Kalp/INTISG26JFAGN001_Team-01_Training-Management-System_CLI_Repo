package com.cognizant.model;

import java.sql.Timestamp;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int trainerId; // Foreign Key
    private Timestamp createdAt;
    private boolean isDeleted;

    public Course() {}

    public Course(String courseName, String description, int trainerId) {
        this.courseName = courseName;
        this.description = description;
        this.trainerId = trainerId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
}