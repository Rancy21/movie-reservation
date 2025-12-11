package com.larr.movie_reservation_app.dto.screen;

public record ScreenDTO(
        String id,
        String theater,
        int screenNumber,
        boolean isActive) {

}
