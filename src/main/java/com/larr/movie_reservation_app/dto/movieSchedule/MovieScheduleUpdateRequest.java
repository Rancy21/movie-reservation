package com.larr.movie_reservation_app.dto.movieSchedule;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MovieScheduleUpdateRequest(
        @NotBlank String movieId,
        String screenId,
        @FutureOrPresent LocalDateTime startTime,
        @Positive Double ticketPrice) {

}
