package com.larr.movie_reservation_app.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
}