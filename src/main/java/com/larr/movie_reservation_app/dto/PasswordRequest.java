package com.larr.movie_reservation_app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
}