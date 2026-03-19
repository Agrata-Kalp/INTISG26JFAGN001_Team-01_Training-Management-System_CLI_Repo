package com.cognizant.daoImpl;

import com.cognizant.config.DatabaseConfig;
import com.cognizant.dao.CertificationDAO;
import com.cognizant.model.Certification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificationDAOImpl implements CertificationDAO {

    @Override
    public boolean issueCertificate(Certification certificate) throws SQLException {
        String query = "INSERT INTO certifications (student_id, course_id, certificate_url) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, certificate.getStudentId());
            pstmt.setInt(2, certificate.getCourseId());
            pstmt.setString(3, certificate.getCertificateUrl());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Certification> getCertificatesByStudent(int studentId) throws SQLException {
        List<Certification> list = new ArrayList<>();
        String query = "SELECT * FROM certifications WHERE student_id = ? AND is_deleted = false";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Certification c = new Certification();
                c.setId(rs.getInt("id"));
                c.setCourseId(rs.getInt("course_id"));
                c.setCertificateUrl(rs.getString("certificate_url"));
                list.add(c);
            }
        }
        return list;
    }
}