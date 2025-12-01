package com.larr.movie_reservation_app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.dto.LoginRequest;
import com.larr.movie_reservation_app.dto.UserRequest;
import com.larr.movie_reservation_app.model.User;
import com.larr.movie_reservation_app.security.jwt.JwtUtils;
import com.larr.movie_reservation_app.service.DtoMapper;
import com.larr.movie_reservation_app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final DtoMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/api/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody UserRequest request) {
        if (service.userExistsByEmail(request.getEmail())) {
            User user = mapper.toUserEntity(request);

            return ResponseEntity.ok(mapper.toUserResponse(service.savUser(user)));

        }
        return ResponseEntity.badRequest().body("User with this email already exists");

    }

    @PostMapping(value = "/api/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        ResponseCookie cookie = jwtUtils.generateJwtCookie(userDetails.getUsername());

        User user = service.getUserByEmail(userDetails.getUsername());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(mapper.toUserResponse(user));

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserinfo(@PathVariable String id) {
        return ResponseEntity.ok(mapper.toUserResponse(service.finUserById(id)));
    }

    @PatchMapping("/users/{id}/name")
    public ResponseEntity<?> updateUserName(@PathVariable String id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(mapper.toUserResponse(service.updateUserName(id, request.getName())));
    }

    @PatchMapping("/users/{id}/phone")
    public ResponseEntity<?> updateUserPhoneNumber(@PathVariable String id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(mapper.toUserResponse(service.updatUserPhoneNumber(id, request.getPhoneNumber())));
    }

    @PatchMapping("/users/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable String id, @RequestBody UserRequest request) {
        service.updatUserPassword(id, request.getPassword());
        return ResponseEntity.ok("Passwored Updated successfully");
    }

}