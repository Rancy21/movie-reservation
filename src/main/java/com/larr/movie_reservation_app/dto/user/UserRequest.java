package com.larr.movie_reservation_app.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
}