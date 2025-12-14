package com.larr.movie_reservation_app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleCreateRequest;
import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleUpdateRequest;
import com.larr.movie_reservation_app.security.service.MovieScheduleService;
import com.larr.movie_reservation_app.service.mapper.MovieScheduleMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MovieScheduleController {

    private final MovieScheduleService service;
    private final MovieScheduleMapper mapper;

    @GetMapping("/api/schedules/{id}")
    public ResponseEntity<?> findMovieSchedule(@PathVariable String id) {
        return ResponseEntity.ok(service.findScheduleDetail(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/schedules")
    public ResponseEntity<?> createSchedule(@RequestBody MovieScheduleCreateRequest request) {
        return ResponseEntity.ok(mapper.toMovieScheduleDTO(service.creatMovieSchedule(request)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/api/schedules/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable String id, @RequestBody MovieScheduleUpdateRequest request) {
        return ResponseEntity.ok(mapper.toMovieScheduleDTO(service.updateMovieSchedule(id, request)));
    }

    @GetMapping("/api/movies/{movieId}/schedules")
    public ResponseEntity<?> listSchedulesByMovie(@PathVariable String movieId) {
        return ResponseEntity.ok(service.findScheduleByMovie(movieId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/screens/{screenId}/schedules")
    public ResponseEntity<?> listSchedulesByScreen(@PathVariable String screenId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "movie") String sortBy) {
        if (date == null) {
            date = LocalDateTime.now().toLocalDate();
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return ResponseEntity.ok(service.findScheduleByScreen(screenId, date, pageable));
    }
}