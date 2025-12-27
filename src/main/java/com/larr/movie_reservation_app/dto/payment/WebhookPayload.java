package com.larr.movie_reservation_app.dto.payment;

import lombok.Data;

@Data
public class WebhookPayload {
    private String event;
    private WebhookData data;

    @Data
    public static class WebhookData {
        private String id;
        private String status;
        private String reference;
        private Double amount;
        private String currency;
        private String customerId;
    }
}
