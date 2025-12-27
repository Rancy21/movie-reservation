package com.larr.movie_reservation_app.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CardPaymentMethodRequest {
    private final String type = "card";
    private CardDetails card;

    @Getter
    @Setter
    @Builder
    public static class CardDetails {
        @JsonProperty("encypted_card_number")
        private String encryptedCardNumber;
        @JsonProperty("encrypted_expiry_month")
        private String encryptedExpiryMonth;
        @JsonProperty("encrypted_expiry_year")
        private String encryptedExpiryYear;
        @JsonProperty("encrypted_cvv")
        private String encryptedCvv;
        private String nonce;
    }
}
