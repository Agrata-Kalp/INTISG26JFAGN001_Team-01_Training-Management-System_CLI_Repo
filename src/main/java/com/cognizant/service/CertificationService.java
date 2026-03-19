package com.cognizant.service;

import com.cognizant.daoImpl.CertificationDAOImpl;
import com.cognizant.model.Certification;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CertificationService {
    private final CertificationDAOImpl certificationDAO = new CertificationDAOImpl();

    public boolean generateCertificate(int studentId, int courseId) throws SQLException {
        // Generate a unique Certificate ID using UUID
        String certUrl = "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Certification cert = new Certification(studentId, courseId, certUrl);
        return certificationDAO.issueCertificate(cert);
    }

    public List<Certification> getMyCertifications(int studentId) throws SQLException {
        return certificationDAO.getCertificatesByStudent(studentId);
    }
}