package com.larr.movie_reservation_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.larr.movie_reservation_app.model.Screen;

public interface ScreenRepository extends JpaRepository<Screen, String> {

}