package com.larr.movie_reservation_app.dto.lock;

import java.time.LocalDateTime;

public record SeatLockDTO(String seatNumber, String movie, LocalDateTime date) {

}
