package com.larr.movie_reservation_app.dto.screen;

import jakarta.validation.constraints.Positive;

public record ScreenRequest(
                @Positive(message = "Screen number should be positive and greater than zero") int screenNumber,
                @Positive(message = "Total number of rows should be positive and greater than zero") int totalRows,
                @Positive(message = "Number of seats per row should be positive and greater than zero") int seatsPerRow) {

}
