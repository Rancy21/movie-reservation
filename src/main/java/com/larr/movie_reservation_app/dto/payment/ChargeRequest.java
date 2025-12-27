package com.larr.movie_reservation_app.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ChargeRequest {
    private String reference;
    private String currency;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    private Double amount;
    @JsonProperty("redirect_url")
    private String redirectUrl;
    private Boolean recurring;
    private AuthorizationDTO authorization;

    @Setter
    @Getter
    @Builder
    public static class AuthorizationDTO {
        private String type;
        private PinDTO pin;
        private OtpDTO otp;

        @Setter
        @Getter
        @Builder
        public static class PinDTO {
            private String nonce;
            @JsonProperty("encrypted_pin")
            private String encryptedPin;
        }

        @Setter
        @Getter
        @Builder
        public static class OtpDTO {
            private String code;
        }
    }
}
