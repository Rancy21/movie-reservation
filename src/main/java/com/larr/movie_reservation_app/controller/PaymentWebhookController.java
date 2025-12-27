package com.larr.movie_reservation_app.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.service.payment.TicketPaymentService;
import com.larr.movie_reservation_app.service.payment.WebhookVerificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
@Slf4j
public class PaymentWebhookController {
    private final TicketPaymentService paymentService;
    private final WebhookVerificationService webhookVerificationService;

    @PostMapping("/flutterwave")
    public ResponseEntity<String> handleFlutterwaveWebhook(
            @RequestBody Map<String, Object> payload,
            @RequestHeader("X-Webhook-Signature") String signature) {

        try {
            // Verify webhook signature
            if (!webhookVerificationService.verifyWebhookSignature(payload, signature)) {
                log.warn("Invalid webhook signature received");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
            }

            // Extract payment data
            @SuppressWarnings("unchecked")
            Map<String, Object> data = (Map<String, Object>) payload.get("data");
            String chargeId = (String) data.get("id");
            String status = (String) data.get("status");
            String reference = (String) data.get("reference");

            log.info("Processing webhook for charge: {} with status: {}", chargeId, status);

            // Process the payment based on status
            paymentService.handlePaymentWebhook(reference, chargeId, status);

            return ResponseEntity.ok("Webhook processed successfully");

        } catch (Exception e) {
            log.error("Error processing webhook: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing webhook: " + e.getMessage());
        }
    }
}
