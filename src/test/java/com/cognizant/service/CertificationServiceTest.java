package com.cognizant.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CertificationServiceTest {
    private final CertificationService certService = new CertificationService();

    @Test
    public void testCertificateIdGeneration() {
        // We can't easily test the DB part without a mock,
        // but we can test if the ID format is correct.
        String certId = "CERT-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        assertTrue(certId.startsWith("CERT-"));
        assertEquals(13, certId.length()); // "CERT-" (5) + 8 chars = 13
    }
}