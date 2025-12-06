package com.larr.movie_reservation_app.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.dto.MovieDTO;
import com.larr.movie_reservation_app.model.Movie;
import com.larr.movie_reservation_app.service.DtoMapper;
import com.larr.movie_reservation_app.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService service;
    private final DtoMapper mapper;

    // Search/filter movies GET /api/movies?genre=Action&title=anytitle
    @GetMapping(value = "/search")
    public ResponseEntity<?> searchMovies(@RequestParam(required = false) String genre,
            @RequestParam(required = false) String title, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "title") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return ResponseEntity.ok(service.searchMovies(genre, title, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewMovieDetails(@PathVariable String id) {
        return ResponseEntity.ok(mapper.toMovieDTO(service.findMovieById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> createMovie(@RequestBody MovieDTO dto) {
        Movie movie = mapper.toMovieEntity(dto);
        return ResponseEntity.ok(mapper.toMovieDTO(service.saveMovie(movie)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable String id, @RequestBody MovieDTO dto) {
        log.info("dto info: " + dto.toString());
        return ResponseEntity.ok(mapper.toMovieDTO(service.updateMovie(id, dto)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMove(@PathVariable String id) {
        return ResponseEntity.ok(mapper.toMovieDTO(service.deleteMovie(id)));
    }

}