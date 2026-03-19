package com.cognizant.service;

import com.cognizant.daoImpl.TrainerDAOImpl;
import com.cognizant.exception.TrainerNotFoundException;
import com.cognizant.exception.UserAlreadyExistsException;
import com.cognizant.model.Trainer;
import java.sql.SQLException;

public class TrainerService {
    private final TrainerDAOImpl trainerDAO = new TrainerDAOImpl();

    public boolean registerTrainer(Trainer trainer) throws UserAlreadyExistsException, SQLException {
        try {
            return trainerDAO.registerTrainer(trainer);
        } catch (SQLException e) {
            // 1062 is MySQL code for Duplicate Entry
            if (e.getErrorCode() == 1062) {
                throw new UserAlreadyExistsException("A trainer with this Email/Username already exists.");
            }
            throw e;
        }
    }

    public Trainer login(String username, String password) throws TrainerNotFoundException, SQLException {
        Trainer t = trainerDAO.loginTrainer(username, password);
        if (t == null) {
            throw new TrainerNotFoundException("Invalid credentials. No active trainer found with those details.");
        }
        return t;
    }
}