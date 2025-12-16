package com.larr.movie_reservation_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.larr.movie_reservation_app.dto.seat.SeatDTO;
import com.larr.movie_reservation_app.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    @Query("from Seat s where s.screen.id = ?1 and s.isOutOfService = false")
    List<Seat> findByScreen(String screenId);

    @Query("""
                select new com.larr.movie_reservation_app.dto.seat.SeatDTO(s.id, s.seatNumber, s.price)
                from Seat s where s.screen.id = ?1
                and s.isOutOfService = false
            """)
    List<SeatDTO> findSeatsByScreen(String screenId);
}