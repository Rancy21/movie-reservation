package com.larr.movie_reservation_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TheaterUpdateRequest(@NotBlank String name, String city, String address,
        @Min(1) int totalScreens) {
}
