package com.larr.movie_reservation_app.dto.theater;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TheaterCreateRequest(@NotBlank String name, @NotBlank String city, @NotBlank String address,
                @Min(1) int totalScreens) {

}
