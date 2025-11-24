package com.larr.movie_reservation_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.larr.movie_reservation_app.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {
}