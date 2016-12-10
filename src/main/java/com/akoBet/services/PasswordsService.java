package com.akoBet.services;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Arek on 09.12.2016.
 */
@Service
public class PasswordsService {
    private SecureRandom generator;
    private String encryptionAlgorithm = "PBEWithMD5AndDES";

    public PasswordsService() throws NoSuchAlgorithmException {
        generator = new SecureRandom();
    }

    public String getNextPasswordSeed() {
        return String.valueOf(generator.nextInt());
    }

    public String encrypt(String password) {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), encryptionAlgorithm);
        return secretKey.getEncoded().toString();
    }
}
