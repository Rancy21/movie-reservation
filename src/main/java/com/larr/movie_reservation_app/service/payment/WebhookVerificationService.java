package com.larr.movie_reservation_app.service.payment;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.larr.movie_reservation_app.config.FlutterwaveConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhookVerificationService {
    private final FlutterwaveConfig flutterwaveConfig;
    private final ObjectMapper objectMapper;

    /**
     * Verify webhook signature from Flutterwave using HMAC SHA256
     * 
     * @param payload The webhook payload as a map
     * @param signature The X-Webhook-Signature header value
     * @return true if signature is valid, false otherwise
     */
    public boolean verifyWebhookSignature(Map<String, Object> payload, String signature) {
        try {
            String webhookSecret = flutterwaveConfig.getWebhookSecret();
            if (webhookSecret == null || webhookSecret.isEmpty()) {
                log.error("Webhook secret not configured");
                return false;
            }

            // Convert payload to JSON string
            String payloadJson = objectMapper.writeValueAsString(payload);

            // Create HMAC SHA256 hash
            String calculatedSignature = hmacSha256(payloadJson, webhookSecret);

            // Compare with provided signature (constant-time comparison to prevent timing attacks)
            return constantTimeEquals(calculatedSignature, signature);

        } catch (Exception e) {
            log.error("Error verifying webhook signature: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Generate HMAC SHA256 hash
     */
    private String hmacSha256(String message, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            javax.crypto.spec.SecretKeySpec secretKey = new javax.crypto.spec.SecretKeySpec(
                    secret.getBytes(StandardCharsets.UTF_8),
                    0,
                    secret.getBytes(StandardCharsets.UTF_8).length,
                    "HmacSHA256");
            mac.init(secretKey);

            byte[] hashBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);

        } catch (Exception e) {
            log.error("Error generating HMAC SHA256: {}", e.getMessage());
            throw new RuntimeException("Failed to generate HMAC SHA256", e);
        }
    }

    /**
     * Convert bytes to hex string
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Constant-time string comparison to prevent timing attacks
     */
    private boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) {
            return a == b;
        }

        byte[] aBytes = a.getBytes(StandardCharsets.UTF_8);
        byte[] bBytes = b.getBytes(StandardCharsets.UTF_8);

        if (aBytes.length != bBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < aBytes.length; i++) {
            result |= aBytes[i] ^ bBytes[i];
        }
        return result == 0;
    }
}
