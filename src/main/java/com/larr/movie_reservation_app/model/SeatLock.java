package com.larr.movie_reservation_app.model;

import lombok.Data;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "seat_lock")
public class SeatLock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private MovieSchedule schedule;
    @Column(name = "locked_at")
    private LocalDateTime lockedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User lockedBy;
    private static final int LOCK_TIMEOUT_MINUTES = 10;

    @PrePersist
    protected void onCreate() {
        lockedAt = LocalDateTime.now();
    }

    public boolean isLockExpired() {
        return lockedAt.plusMinutes(LOCK_TIMEOUT_MINUTES).isBefore(LocalDateTime.now());
    }
}
