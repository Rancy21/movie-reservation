package com.larr.movie_reservation_app.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String email;
    private String name;
    private String phoneNumber;
}