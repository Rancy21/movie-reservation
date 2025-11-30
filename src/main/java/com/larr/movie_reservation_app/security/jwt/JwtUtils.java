package com.larr.movie_reservation_app.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component

public class JwtUtils {
  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration}")
  private int jwtExp;

  // create a secret key for signing/verifying jwts
  private SecretKey Key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  // create a signed JWT for a user
  public String generateJwtToken(String userEmail) {
    return Jwts.builder().subject(userEmail).issuedAt(new Date()).expiration(new Date((new Date().getTime() + jwtExp)))
        .signWith(Key()).compact();
  }

  // extract token from cookie
  public String getJwtFromCookie(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, "jwt");
    if (cookie != null) {
      return cookie.getValue();
    }
    return null;
  }

  // create an HTTP-only cookie
  public ResponseCookie generateJwtCookie(String userEmail) {
    String jwt = generateJwtToken(userEmail);

    return ResponseCookie.from("jwt", jwt).path("/").maxAge(jwtExp / 1000).httpOnly(true).secure(true).sameSite("LAX")
        .build();
  }

  // Clears JWT Cookie
  public ResponseCookie generateCleanCookie() {
    return ResponseCookie.from("jwt", "").maxAge(0).httpOnly(true).secure(true).sameSite("LAX")
        .build();
  }

  // Extract email from
  public String getEmailFromToken(String token) {
    return Jwts.parser().verifyWith(Key()).build().parseSignedClaims(token).getPayload().getSubject();
  }

  // Verifies token’s integrity and validity
  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().verifyWith(Key()).build().parse(authToken);
      return true;
    } catch (ExpiredJwtException e) {
      throw new JwtValidationException("Token expired", e);
    } catch (UnsupportedJwtException e) {
      throw new JwtValidationException("Unsupported token", e);
    } catch (MalformedJwtException e) {
      throw new JwtValidationException("Invalid token", e);
    } catch (IllegalArgumentException e) {
      throw new JwtValidationException("Token is empty or null", e);
    }
  }

}