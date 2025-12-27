package com.larr.movie_reservation_app.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRequest {
    private String email;
    private NameDTO name;
    private PhoneDTO phone;
    private AddressDTO address;

    @Data
    @Builder
    public static class NameDTO {
        private String first;
        private String middle;
        private String last;
    }

    @Data
    @Builder
    public static class PhoneDTO {
        @JsonProperty("country_code")
        private String countryCode;
        private String number;
    }

    @Data
    @Builder
    public static class AddressDTO {
        private String city;
        private String country;
        private String line1;
        private String line2;
        @JsonProperty("postal_code")
        private String postalCode;
        private String state;
    }
}
