package com.larr.movie_reservation_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.larr.movie_reservation_app.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query("select m from Movie m where " +
            "(:genre is null or m.genre = :genre)" +
            "and (:title is null or lower(m.title) ilike lower(concat('%', :title, '%')))")
    Page<Movie> search(String genre, String title, Pageable pageable);

}