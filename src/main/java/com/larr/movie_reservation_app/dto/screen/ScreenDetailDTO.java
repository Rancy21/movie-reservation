package com.larr.movie_reservation_app.dto.screen;

public record ScreenDetailDTO(
        String theater,
        int screenNumber,
        int totalRows,
        int seatsPerRow) {
}
