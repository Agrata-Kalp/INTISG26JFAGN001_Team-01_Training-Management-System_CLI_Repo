package com.cognizant.daoImpl;

import com.cognizant.config.DatabaseConfig;
import com.cognizant.dao.TrainerDAO;
import com.cognizant.model.Trainer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAOImpl implements TrainerDAO {

    @Override
    public boolean registerTrainer(Trainer trainer) throws SQLException {
        String query = "INSERT INTO trainers (username, email, mobile_number, password, specialization) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, trainer.getUsername());
            pstmt.setString(2, trainer.getEmail());
            pstmt.setString(3, trainer.getMobileNumber());
            pstmt.setString(4, trainer.getPassword());
            pstmt.setString(5, trainer.getSpecialization());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public Trainer loginTrainer(String username, String password) throws SQLException {
        String query = "SELECT * FROM trainers WHERE username = ? AND password = ? AND is_deleted = false";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Trainer t = new Trainer();
                t.setId(rs.getInt("id"));
                t.setUsername(rs.getString("username"));
                t.setSpecialization(rs.getString("specialization"));
                return t;
            }
        }
        return null;
    }

    @Override
    public Trainer getTrainerById(int id) throws SQLException {
        String query = "SELECT * FROM trainers WHERE id = ? AND is_deleted = false";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Trainer t = new Trainer();
                t.setId(rs.getInt("id"));
                t.setUsername(rs.getString("username"));
                t.setEmail(rs.getString("email"));
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Trainer> getAllActiveTrainers() throws SQLException {
        List<Trainer> trainers = new ArrayList<>();
        String query = "SELECT * FROM trainers WHERE is_deleted = false";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Trainer t = new Trainer();
                t.setId(rs.getInt("id"));
                t.setUsername(rs.getString("username"));
                trainers.add(t);
            }
        }
        return trainers;
    }

    @Override
    public boolean softDeleteTrainer(int id) throws SQLException {
        String query = "UPDATE trainers SET is_deleted = true WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}