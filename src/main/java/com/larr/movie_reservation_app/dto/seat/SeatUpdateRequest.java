package com.larr.movie_reservation_app.dto.seat;

import jakarta.validation.constraints.Positive;

public record SeatUpdateRequest(@Positive Double newPrice) {

}
