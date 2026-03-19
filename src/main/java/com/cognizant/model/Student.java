package com.cognizant.model;

import java.sql.Timestamp;

public class Student {
    private int id;
    private String username;
    private String email;
    private String mobileNumber;
    private String password;
    private Timestamp createdAt;
    private boolean isDeleted;

    // Default Constructor
    public Student() {}

    // Constructor for Registration (ID and Timestamps are handled by DB)
    public Student(String username, String email, String mobileNumber, String password) {
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    // Getters and Seters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }
}