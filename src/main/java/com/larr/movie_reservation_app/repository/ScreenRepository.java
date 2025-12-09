package com.larr.movie_reservation_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.larr.movie_reservation_app.model.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, String> {

}