package com.larr.movie_reservation_app.service.payment;

import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.larr.movie_reservation_app.config.FlutterwaveConfig;
import com.larr.movie_reservation_app.dto.payment.CardPaymentMethodRequest;
import com.larr.movie_reservation_app.dto.payment.ChargeRequest;
import com.larr.movie_reservation_app.dto.payment.CustomerRequest;
import com.larr.movie_reservation_app.dto.payment.FlutterwaveResponse;
import com.larr.movie_reservation_app.dto.payment.MobileMoneyPaymentMethodRequest;
import com.larr.movie_reservation_app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlutterwaveService {
    private final FlutterwaveConfig config;
    private final RestTemplate restTemplate;
    private final TokenManager tokenManager;
    private final EncryptionService encryptionService;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenManager.getToken());
        headers.set("X-Trace-Id", UUID.randomUUID().toString());
        headers.set("X-Idempotency-key", UUID.randomUUID().toString());
        return headers;
    }

    // Create Customer
    public FlutterwaveResponse<Object> createCustomer(CustomerRequest request) {
        String url = config.getBaseUrl() + "/customers";

        HttpEntity<CustomerRequest> entity = new HttpEntity<>(request, createHeaders());

        ResponseEntity<FlutterwaveResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                FlutterwaveResponse.class);
        return response.getBody();
    }

    // Create Card Payment Method
    public FlutterwaveResponse<Object> createCardPaymentMethod(String cardNumber, String expiryMonth, String expiryYear,
            String cvv) {
        String nonce = encryptionService.generateNonce(12);
        CardPaymentMethodRequest request = CardPaymentMethodRequest.builder()
                .card(CardPaymentMethodRequest.CardDetails.builder()
                        .encryptedCardNumber(encryptionService.encrypt(cardNumber, nonce, config.getEncryptionKey()))
                        .encryptedExpiryMonth(encryptionService.encrypt(expiryMonth, nonce, config.getEncryptionKey()))
                        .encryptedExpiryYear(encryptionService.encrypt(expiryYear, nonce, config.getClientId()))
                        .encryptedCvv(encryptionService.encrypt(cvv, nonce, config.getEncryptionKey())).nonce(nonce)
                        .build())
                .build();

        String url = config.getBaseUrl() + "/payment-methods";
        HttpEntity<CardPaymentMethodRequest> entity = new HttpEntity<>(request, createHeaders());

        ResponseEntity<FlutterwaveResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, FlutterwaveResponse.class);

        return response.getBody();
    }

    // Create Mobile Money Payment Method
    public FlutterwaveResponse<Object> createMobilePaymentMethod(String countryCode, String network,
            String phoneNumber) {
        MobileMoneyPaymentMethodRequest request = MobileMoneyPaymentMethodRequest.builder()
                .mobileMoney(MobileMoneyPaymentMethodRequest.MobileMoneyDetails.builder()
                        .countryCode(countryCode).network(network).phoneNumber(phoneNumber).build())
                .build();

        String url = config.getBaseUrl() + "/payment-methods";
        HttpEntity<MobileMoneyPaymentMethodRequest> entity = new HttpEntity<>(request, createHeaders());

        ResponseEntity<FlutterwaveResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, FlutterwaveResponse.class);

        return response.getBody();
    }

    // Create Charge
    public FlutterwaveResponse<Object> createCharge(ChargeRequest request) {
        String url = config.getBaseUrl() + "/charges";
        HttpEntity<ChargeRequest> entity = new HttpEntity<>(request, createHeaders());

        ResponseEntity<FlutterwaveResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, FlutterwaveResponse.class);

        return response.getBody();
    }

    // Verify Charge
    public FlutterwaveResponse<Object> verifyCharge(String chargeId) {
        String url = config.getBaseUrl() + "/charges/" + chargeId;
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        ResponseEntity<FlutterwaveResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, FlutterwaveResponse.class);

        return response.getBody();
    }

}
