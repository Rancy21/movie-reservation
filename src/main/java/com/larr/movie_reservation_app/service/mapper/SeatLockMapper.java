package com.larr.movie_reservation_app.service.mapper;

import org.springframework.stereotype.Component;

import com.larr.movie_reservation_app.dto.lock.SeatLockDTO;
import com.larr.movie_reservation_app.model.SeatLock;

@Component
public class SeatLockMapper {
    public SeatLockDTO toDTO(SeatLock lock) {
        return new SeatLockDTO(lock.getSeat().getSeatNumber(), lock.getSchedule().getMovie().getTitle(),
                lock.getSchedule().getStartTime());
    }
}
