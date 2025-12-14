package com.larr.movie_reservation_app.security.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleCreateRequest;
import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleDTO;
import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleDetailDTO;
import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleUpdateRequest;
import com.larr.movie_reservation_app.exception.MovieScheduleNotFoundException;
import com.larr.movie_reservation_app.model.Movie;
import com.larr.movie_reservation_app.model.MovieSchedule;
import com.larr.movie_reservation_app.repository.MovieScheduleRepository;
import com.larr.movie_reservation_app.service.MovieService;
import com.larr.movie_reservation_app.service.ScreenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieScheduleService {
    private final MovieScheduleRepository repository;
    private final ScreenService screenService;
    private final MovieService movieService;

    public MovieSchedule findScheduleById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new MovieScheduleNotFoundException("schedule with id: " + id + " not found"));
    }

    public MovieScheduleDetailDTO findScheduleDetail(String id) {
        return repository.findScheduleDetail(id)
                .orElseThrow(() -> new MovieScheduleNotFoundException("schedule with id: " + id + " not found"));
    }

    public MovieSchedule creatMovieSchedule(MovieScheduleCreateRequest request) {
        MovieSchedule schedule = new MovieSchedule();
        Movie movie = movieService.findMovieById(request.movieId());

        schedule.setMovie(movie);
        schedule.setScreen(screenService.findScreenById(request.screenId()));
        schedule.setStartTime(request.startTime());
        schedule.setEndTime(schedule.getStartTime().plusMinutes(movie.getDuration()));
        schedule.setTicketPrice(request.ticketPrice());

        return repository.save(schedule);
    }

    public MovieSchedule updateMovieSchedule(String id, MovieScheduleUpdateRequest request) {
        MovieSchedule schedule = findScheduleById(id);
        Movie movie = schedule.getMovie();
        if (request.movieId() != null) {
            movie = movieService.findMovieById(request.movieId());
            schedule.setMovie(movie);
        }

        if (request.screenId() != null) {
            schedule.setScreen(screenService.findScreenById(id));
        }

        if (request.startTime() != null) {
            schedule.setStartTime(request.startTime());
            schedule.setEndTime(schedule.getStartTime().plusMinutes(movie.getDuration()));
        }

        if (request.ticketPrice() != null) {
            schedule.setTicketPrice(request.ticketPrice());
        }

        return repository.save(schedule);
    }

    public Page<MovieScheduleDTO> findScheduleByScreen(String screenId, LocalDate date, Pageable pageable) {
        LocalDateTime start = date.atStartOfDay();

        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return repository.findByScreen(screenId, start, end, pageable);
    }

    public List<MovieScheduleDTO> findScheduleByMovie(String movieId) {
        return repository.findByMovie(movieId);
    }
}
