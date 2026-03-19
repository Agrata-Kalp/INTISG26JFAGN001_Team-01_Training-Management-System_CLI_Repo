package com.cognizant.dao;

import com.cognizant.model.Trainer;
import java.sql.SQLException;
import java.util.List;

public interface TrainerDAO {
    // Basic Registration and Login
    boolean registerTrainer(Trainer trainer) throws SQLException;
    Trainer loginTrainer(String username, String password) throws SQLException;

    // Additional features for the Trainer Service
    Trainer getTrainerById(int id) throws SQLException;
    List<Trainer> getAllActiveTrainers() throws SQLException;
    boolean softDeleteTrainer(int id) throws SQLException;
}