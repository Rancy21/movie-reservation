package com.larr.movie_reservation_app.dto.payment;

public record CardDetails(String email,
        String cardNumber,
        String expiryMonth,
        String expiryYear,
        String cvv) {

}
