package com.larr.movie_reservation_app.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MobileMoneyPaymentMethodRequest {
    private final String type = "mobile_money";
    @JsonProperty("mobile_money")
    private MobileMoneyDetails mobileMoney;

    @Setter
    @Getter
    @Builder
    public static class MobileMoneyDetails {
        @JsonProperty("country_code")
        private String countryCode;
        private String network;
        @JsonProperty("phone_number")
        private String phoneNumber;
    }
}
