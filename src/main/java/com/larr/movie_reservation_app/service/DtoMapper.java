package com.larr.movie_reservation_app.service;

import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.dto.UserRequest;
import com.larr.movie_reservation_app.dto.UserResponse;
import com.larr.movie_reservation_app.model.Role;
import com.larr.movie_reservation_app.model.User;

@Service
public class DtoMapper {
    public User toUserEntity(UserRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        return user;
    }

    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        return response;
    }

}