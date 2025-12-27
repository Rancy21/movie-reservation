package com.larr.movie_reservation_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.larr.movie_reservation_app.model.Ticket;
import com.larr.movie_reservation_app.model.TicketStatus;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    @Query("From Ticket t where t.id = ?1 and t.status = ?2")
    Optional<Ticket> findTicketByIdAndStatus(String id, TicketStatus status);

    @Modifying
    @Query(value = "update ticket set status = 'CANCELLED' where now() - booking_time > interval '7 minutes'", nativeQuery = true)
    void cleanupTickets();
}