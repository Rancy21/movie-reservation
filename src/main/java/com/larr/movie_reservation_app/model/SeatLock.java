package com.larr.movie_reservation_app.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SeatLock {
    private Seat seat;
    private MovieSchedule schedule;
    private LocalDateTime lockedAt;
    private User lockedBy;
    private static final int LOCK_TIMEOUT_MINUTES = 10;

    public boolean isLockExpired() {
        return lockedAt.plusMinutes(LOCK_TIMEOUT_MINUTES).isBefore(LocalDateTime.now());
    }
}
