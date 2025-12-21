package com.larr.movie_reservation_app.service;

import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.exception.LockedSeatException;
import com.larr.movie_reservation_app.model.MovieSchedule;
import com.larr.movie_reservation_app.model.Seat;
import com.larr.movie_reservation_app.model.SeatLock;
import com.larr.movie_reservation_app.model.User;
import com.larr.movie_reservation_app.repository.SeatLockRepository;
import com.larr.movie_reservation_app.security.service.MovieScheduleService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatLockService {
    private final SeatLockRepository repository;
    private final SeatService seatService;
    private final MovieScheduleService scheduleService;
    private final UserService userService;

    public boolean isSeatLocked(String seatId, String scheduleId) {
        return repository.findSeatLock(seatId, scheduleId).isPresent();
    }

    public SeatLock lockSeat(String seatId, String scheduleId, String email) {
        if (isSeatLocked(seatId, scheduleId)) {
            throw new LockedSeatException("This seat is already locked");
        }
        User user = userService.getUserByEmail(email);
        SeatLock lock = new SeatLock();
        Seat seat = seatService.findSeatById(seatId);
        MovieSchedule schedule = scheduleService.findScheduleById(scheduleId);

        lock.setSeat(seat);
        lock.setSchedule(schedule);
        lock.setLockedBy(user);

        return repository.save(lock);

    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void cleanUplocks() {
        repository.deleteExpiredLocks();
        return;
    }

    @Transactional
    public void deleteLocks(Set<String> seatIds, String scheduleId) {
        for (String id : seatIds) {
            repository.cleanUpLock(id, scheduleId);
        }
    }

}
