package com.larr.movie_reservation_app.dto.seat;

import java.math.BigDecimal;

public record SeatAvailabilityDTO(
                String seatId,
                String seatNumber,
                String seatRow,
                Integer number,
                BigDecimal price,
                boolean isAvailable) {
}
