package com.larr.movie_reservation_app.dto.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private String status; // "success", "pending", "requires_action"
    private String message;
    private String chargeId;
    private String redirectUrl;
    private String nextAction; // "redirect", "pin", "otp", "wait", "none"

    public static PaymentResponse success(String chargeId, String message) {
        return PaymentResponse.builder()
                .status("success")
                .message(message)
                .chargeId(chargeId)
                .nextAction("none")
                .build();
    }

    public static PaymentResponse requiresRedirect(String chargeId, String url) {
        return PaymentResponse.builder()
                .status("requires_action")
                .message("Please complete authorization")
                .chargeId(chargeId)
                .redirectUrl(url)
                .nextAction("redirect")
                .build();
    }

    public static PaymentResponse requiresPin(String chargeId) {
        return PaymentResponse.builder()
                .status("requires_action")
                .message("Please provide your PIN")
                .chargeId(chargeId)
                .nextAction("pin")
                .build();
    }

    public static PaymentResponse requiresOtp(String chargeId) {
        return PaymentResponse.builder()
                .status("requires_action")
                .message("Please enter OTP")
                .chargeId(chargeId)
                .nextAction("otp")
                .build();
    }

    public static PaymentResponse pending(String chargeId, String message) {
        return PaymentResponse.builder()
                .status("pending")
                .message(message)
                .chargeId(chargeId)
                .nextAction("wait")
                .build();
    }
}
