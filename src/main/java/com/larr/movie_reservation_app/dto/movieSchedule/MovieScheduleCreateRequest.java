package com.larr.movie_reservation_app.dto.movieSchedule;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovieScheduleCreateRequest(
                @NotBlank String movieId,
                @NotBlank String screenId,
                @NotNull @FutureOrPresent LocalDateTime startTime,
                @NotNull @Positive double ticketPrice) {

}
