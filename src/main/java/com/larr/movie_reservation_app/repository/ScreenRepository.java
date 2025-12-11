package com.larr.movie_reservation_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.larr.movie_reservation_app.dto.screen.ScreenDTO;
import com.larr.movie_reservation_app.dto.screen.ScreenDetailDTO;
import com.larr.movie_reservation_app.model.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, String> {
    @Query("""
            select new com.larr.movie_reservation_app.dto.screen.ScreenDetailDTO(
            s.theater.name,
            s.screenNumber,
            s.totalRows,
            s.seatsPerRow
            )
            From Screen s
            Where s.id = ?1
            and s.theater.id = ?2
            """)
    Optional<ScreenDetailDTO> findScreenDetail(String id, String theaterId);

    @Query("""
            select new com.larr.movie_reservation_app.dto.screen.ScreenDTO(
            s.id,
            s.theater.name,
            s.screenNumber,
            s.isActive
            )
            FROM Screen s
            WHERE s.theater.id = ?1
            """)
    List<ScreenDTO> listAllScreenByTheater(String theaterId);
}