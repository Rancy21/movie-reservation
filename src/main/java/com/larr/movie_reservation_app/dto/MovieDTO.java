package com.larr.movie_reservation_app.dto;

import com.larr.movie_reservation_app.model.MovieStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieDTO {
    String id;
    private String title;
    private String genre;
    private int duration;
    private String rating;
    private String description;
    private String posterUrl;
    private boolean isActive;
    private MovieStatus status;
}