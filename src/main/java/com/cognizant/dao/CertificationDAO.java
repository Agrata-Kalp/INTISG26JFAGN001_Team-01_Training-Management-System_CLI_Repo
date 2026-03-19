package com.cognizant.dao;

import com.cognizant.model.Certification;
import java.sql.SQLException;
import java.util.List;

public interface CertificationDAO {
    boolean issueCertificate(Certification certificate) throws SQLException;
    List<Certification> getCertificatesByStudent(int studentId) throws SQLException;
}