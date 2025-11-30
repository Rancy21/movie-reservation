package com.larr.movie_reservation_app.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.exception.UserNotFoundException;
import com.larr.movie_reservation_app.model.User;
import com.larr.movie_reservation_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repo;

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public User savUser(User user) {
    String hashedPassword = encoder.encode(user.getPassword());

    user.setPassword(hashedPassword);

    return repo.save(user);
  }

  public User updatUserPassword(String userId, String newPassword) {
    User user = finUserById(userId);

    String hashedPassword = encoder.encode(newPassword);

    user.setPassword(hashedPassword);

    return repo.save(user);
  }

  public User updateUserName(String userId, String name) {
    User user = finUserById(userId);

    user.setName(name);

    return repo.save(user);
  }

  public User updatUserPhoneNumber(String userId, String phoneNumber) {
    User user = finUserById(userId);

    user.setPhoneNumber(phoneNumber);

    return repo.save(user);
  }

  public User getUserByEmail(String email) {
    return repo.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("User with email:" + email + " not found"));
  }

  public User finUserById(String id) {
    return repo.findById(id).orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
  }

  public void deleteById(String id) {

    User user = finUserById(id);

    repo.delete(user);
  }

  public List<User> findAllUsers() {
    return repo.findAll();
  }

}