package com.larr.movie_reservation_app.security;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  /**
   * Define what happens when a user try to access a protected resource without
   * being authenticated
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    // Log a message detailling why authentication failed
    log.error("Unauthorized Error: {}", authException.getMessage());

    // Get possible JWT message
    String jwtErrorMessage = (String) request.getAttribute("jwtErrorMessage");
    String message = (jwtErrorMessage != null) ? jwtErrorMessage : authException.getMessage();

    // Set HTTP Status to 401 Unauthorized
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    // Create a json body for the response
    Map<String, Object> body = new HashMap<>();
    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
    body.put("error", "Unauthorized");
    body.put("Message", message);
    body.put("path", request.getServletPath());

    // Write Json body to response
    ObjectMapper mapper = new ObjectMapper();

    mapper.writeValue(response.getOutputStream(), body);
  }

}