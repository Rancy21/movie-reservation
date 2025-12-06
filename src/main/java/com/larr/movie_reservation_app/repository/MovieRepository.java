package com.larr.movie_reservation_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.larr.movie_reservation_app.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query(value = "select * from Movie m where " +
            "(:genre is null or m.genre = :genre)" +
            "and (:title is null or lower(m.title) ilike lower(concat('%', :title, '%')))", nativeQuery = true)
    Page<Movie> search(@Param("genre") String genre, @Param("title") String title, Pageable pageable);

}