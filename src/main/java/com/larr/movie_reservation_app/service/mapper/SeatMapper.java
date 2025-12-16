package com.larr.movie_reservation_app.service.mapper;

import org.springframework.stereotype.Component;

import com.larr.movie_reservation_app.dto.seat.SeatDTO;
import com.larr.movie_reservation_app.model.Seat;

@Component
public class SeatMapper {
    public SeatDTO toSeatDTO(Seat seat) {
        return new SeatDTO(seat.getId(), seat.getSeatNumber(), seat.getPrice());
    }
}
