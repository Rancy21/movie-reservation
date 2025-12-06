package com.larr.movie_reservation_app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.dto.MovieDTO;
import com.larr.movie_reservation_app.dto.MovieDetailDTO;
import com.larr.movie_reservation_app.exception.MovieNotFoundException;
import com.larr.movie_reservation_app.model.Movie;
import com.larr.movie_reservation_app.model.MovieStatus;
import com.larr.movie_reservation_app.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repo;

    public Movie saveMovie(Movie movie) {
        return repo.save(movie);
    }

    public Movie findMovieById(String id) {
        return repo.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie with id: " + id + " not found"));
    }

    public Movie updateMovie(String id, MovieDTO dto) {
        Movie movie = findMovieById(id);
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setDuration(dto.getDuration());
        movie.setRating(dto.getRating());
        movie.setDescription(dto.getDescription());
        movie.setPosterUrl(dto.getPosterUrl());

        return repo.save(movie);
    }

    public Movie deleteMovie(String id) {
        Movie movie = findMovieById(id);

        movie.setActive(false);

        return repo.save(movie);
    }

    public Page<MovieDetailDTO> searchMovies(String genre, String title, Pageable pageable) {
        return repo.search(genre, title, pageable);
    }

    public Movie updateMovieStatus(String id, MovieStatus status) {
        Movie movie = findMovieById(id);

        movie.setStatus(status);

        return repo.save(movie);
    }

}