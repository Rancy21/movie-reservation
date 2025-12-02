package com.larr.movie_reservation_app.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.larr.movie_reservation_app.security.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT authentication filter.
 *
 * This filter runs on EVERY request and:
 * 1. Extracts JWT token from cookie
 * 2. Validates the token
 * 3. Loads user details from database
 * 4. Sets authentication in SecurityContext
 *
 * Once JWT is set, the authentication method doesn't matter.
 */

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
  private final JwtUtils jwtUtils;
  private final UserDetailsServiceImpl service;

  /**
   * Filter logic executed on every request.
   *
   * Flow:
   * 1. Request arrives with JWT cookie
   * 2. This filter executes FIRST (before controllers)
   * 3. Extract and validate JWT
   * 4. Load user from database using email in JWT
   * 5. Set authentication in SecurityContext
   * 6. Pass request to next filter/controller
   *
   * Now controllers can access the authenticated user via:
   * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   *
   * @param request     HTTP request
   * @param response    HTTP response
   * @param filterChain Filter chain to continue processing
   * @throws ServletException if servlet error occurs
   * @throws IOException      if I/O error occurs
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {

      // Retrieve token from the request
      String token = jwtUtils.getJwtFromCookie(request);

      if (token != null && jwtUtils.validateJwtToken(token)) {
        // Extract user email from token
        String useEmail = jwtUtils.getEmailFromToken(token);

        // fetch user details from the database
        UserDetails userDetails = service.loadUserByUsername(useEmail);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Store authenticated user in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (JwtValidationException e) {
      log.error("JWT error: {}", e.getMessage());
      request.setAttribute("jWtErrorMessage", e.getMessage());
    } catch (Exception e) {
      log.error("cannot set user Authentication: {}", e.getMessage());
    }

    filterChain.doFilter(request, response);
  }

}