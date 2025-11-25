package com.larr.movie_reservation_app.security.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.model.User;
import com.larr.movie_reservation_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository repo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = repo.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User with email" + email + " not found"));

    // Convert user's role into GrantedAuthrority
    List<GrantedAuthority> authorities = Collections
        .singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

    // Return UserDetails object
    return org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword())
        .authorities(authorities).build();
  }

}