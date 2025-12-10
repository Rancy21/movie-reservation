package com.larr.movie_reservation_app.dto.theater;

public record TheaterDetailDTO(
                String id,
                String name,
                String address,
                String city,
                int totalScreens) {
}
