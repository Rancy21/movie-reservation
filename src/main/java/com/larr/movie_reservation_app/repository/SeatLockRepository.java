package com.larr.movie_reservation_app.repository;

import org.springframework.stereotype.Repository;

import com.larr.movie_reservation_app.model.SeatLock;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SeatLockRepository extends JpaRepository<SeatLock, String> {
    @Query("From SeatLock sl where sl.seat.id=?1 and sl.schedule.id = ?2")
    Optional<SeatLock> findSeatLock(String seatId, String scheduleId);

    @Modifying
    @Query("Delete From SeatLock sl where sl.seat.id=?1 and sl.schedule.id = ?2")
    void cleanUpLock(String seatId, String scheduleId);

    @Modifying
    @Query(value = "delete from seat_lock where now() - locked_at > interval '5 minutes'", nativeQuery = true)
    void deleteExpiredLocks();

}
