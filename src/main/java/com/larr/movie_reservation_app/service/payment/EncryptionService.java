package com.larr.movie_reservation_app.service.payment;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    private static final String AES_ALGORITHM = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String encrypt(String plainText, String nonce, String base64Key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes());

            byte[] decodedKey = Base64.getDecoder().decode(base64Key);
            SecretKey keySpec = new SecretKeySpec(decodedKey, "AES");

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Enctyption failed: " + e.getMessage());
        }
    }

    public String generateNonce(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder nonce = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            nonce.append(CHARSET.charAt(random.nextInt(CHARSET.length())));
        }
        return nonce.toString();
    }
}
