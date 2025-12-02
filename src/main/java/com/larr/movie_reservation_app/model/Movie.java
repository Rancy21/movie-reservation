package com.larr.movie_reservation_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String genre;
    // duration in minutes
    private int duration;
    private String rating;
    private String description;
    @Column(name = "poster_url")
    private String posterUrl;
    @Column(name = "is_active")
    private boolean isActive;
}
