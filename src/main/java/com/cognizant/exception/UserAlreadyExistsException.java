package com.cognizant.exception;

// Custom exception for duplicate data or registration issues
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}


