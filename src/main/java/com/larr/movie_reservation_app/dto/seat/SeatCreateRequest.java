package com.larr.movie_reservation_app.dto.seat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SeatCreateRequest(@NotBlank String screenId, @Positive @NotNull Double basePrice) {

}
