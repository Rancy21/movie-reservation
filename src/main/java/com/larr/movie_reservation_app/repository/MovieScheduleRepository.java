package com.larr.movie_reservation_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.larr.movie_reservation_app.model.MovieSchedule;

public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, String> {

}