package com.larr.movie_reservation_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleDTO;
import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleDetailDTO;
import com.larr.movie_reservation_app.model.MovieSchedule;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Repository
public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, String> {
        @Query("""
                        select new com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleDTO(sc.id,sc.movie.title, sc.screen.screenNumber, sc.startTime)
                        from MovieSchedule sc
                        where sc.screen.id = ?1
                        and (sc.startTime >= ?2 and sc.startTime < ?3)
                                    """)
        Page<MovieScheduleDTO> findByScreen(String screenId, LocalDateTime start, LocalDateTime end, Pageable pageable);

        @Query("""
                        select new com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleDTO(sc.id,sc.movie.title, sc.screen.screenNumber, sc.startTime)
                         from MovieSchedule sc
                         where sc.movie.id = ?1
                         """)
        List<MovieScheduleDTO> findByMovie(String movieId);

        @Query("""
                        select new com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleDetailDTO(
                        sc.movie.title,
                        sc.screen.theater.name,
                        sc.screen.screenNumber,
                        sc.startTime,
                        sc.ticketPrice
                        )
                        From MovieSchedule sc
                        Where sc.id = ?1
                        """)
        Optional<MovieScheduleDetailDTO> findScheduleDetail(String id);
}