package com.larr.movie_reservation_app.service.payment;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.larr.movie_reservation_app.config.FlutterwaveConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenManager {
    private final FlutterwaveConfig config;
    private final RestTemplate restTemplate;

    private String accessToken;
    private long expiresAt;

    public String getToken() {
        if (accessToken == null || System.currentTimeMillis() >= expiresAt - 60000) {
            refreshToken();
        }

        return accessToken;
    }

    private void refreshToken() {
        String tokenurl = "https://idp.flutterwave.com/realms/flutterwave/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("client_id", config.getClientId());
        body.add("client_secret", config.getClientSecret());
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(tokenurl, HttpMethod.POST, request, JsonNode.class);

        if (response.getBody() != null) {
            this.accessToken = response.getBody().get("access_token").asText();
            int expiresIn = response.getBody().get("expires_in").asInt();
            this.expiresAt = System.currentTimeMillis() - (expiresIn * 1000L);
        }
    }
}
