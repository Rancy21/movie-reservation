package com.larr.movie_reservation_app.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.larr.movie_reservation_app.security.jwt.JwtFiler;
import com.larr.movie_reservation_app.security.jwt.JwtUtils;
import com.larr.movie_reservation_app.security.service.UserDetailsServiceImpl;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity // enable method-level security(@Preauthorize)
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final UserDetailsServiceImpl userDetailsService;
  private final JwtUtils jwtUtils;
  private final AuthEntryPointJwt authEntryPoint;

  // Define a bean for your jwt filter
  @Bean
  public JwtFiler jwtFiler() {
    return new JwtFiler(jwtUtils, userDetailsService);
  }

  // Define a password encoder for password hashing and validation
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Define authentication provider
  // It tells Spring Security to retrieve user from database and validate password
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);

    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  // Authentication Manager
  // Needed to perform authentication manually(e.g, in the controller)
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  // Configure Cross Origin Resource Sharing(CORS) for frontend
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    // Allow Request from frontend server (Adjust port as needed)
    configuration.setAllowedOrigins(List.of("http://localhost:3000"));

    // Allow sending cookies / credentials
    configuration.setAllowCredentials(true);

    // Allow common HTTP methods
    configuration.setAllowedMethods(List.of("POST", "GET", "PATCH", "DELETE", "OPTIONS"));

    // Allow specific headers in request
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));

    // Apply the configuration to all paths
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

  /**
   * Main security filter chain configuration.
   *
   * This is where we configure:
   * - URL access rules (public vs authenticated)
   * - JWT filter integration
   * - Session management (stateless)
   *
   * @param http HttpSecurity builder
   * @return SecurityFilterChain configured security chain
   * @throws Exception if configuration fails
   */

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)// disable CSRF as it is not needed for a stateless API
        // user custom 401 handler
        .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
        .authorizeHttpRequests(auth ->
        // allow internal dispatcher types
        auth.dispatcherTypeMatchers(DispatcherType.ERROR, DispatcherType.FORWARD).permitAll()
            // alllow public resources and routes
            .requestMatchers("/", "/auth.html", "/forget-password.html", "/reset-password.html").permitAll()
            // allow public endpoints access for login / register
            .requestMatchers("/api/auth/**").permitAll()
            // allow Spring's error endpoint
            .requestMatchers("/error").permitAll()
            // require authenticaion for everything else
            .anyRequest().authenticated());

    // Add jwt filter before the main Spring's builtin authentication filter
    // Ensure that jwt are processed before email / password
    http.addFilterBefore(jwtFiler(), UsernamePasswordAuthenticationFilter.class);

    // apply CORS configuration
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

    return http.build();
  }

}