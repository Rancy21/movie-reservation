package com.larr.movie_reservation_app.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private MovieSchedule schedule;
    @ManyToMany
    @JoinTable(name = "ticket_seats", joinColumns = @JoinColumn(name = "ticket_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seats;
    @Column(name = "booking_time")
    private LocalDateTime bookingTime;
    @Column(name = "total_price")
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Theater getTheater() {
        return schedule.getScreen().getTheater();
    }
}
