package com.cognizant.model;

import java.sql.Timestamp;

public class Trainer {
    private int id;
    private String username;
    private String email;
    private String mobileNumber;
    private String password;
    private String specialization;
    private Timestamp createdAt;
    private boolean isDeleted;

    public Trainer() {}

    public Trainer(String username, String email, String mobileNumber, String password, String specialization) {
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.specialization = specialization;
    }

    // Getters and Setters (Standard boilerplate)
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
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}