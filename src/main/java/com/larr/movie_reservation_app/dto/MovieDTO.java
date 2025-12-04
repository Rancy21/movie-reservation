package com.larr.movie_reservation_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
    private String title;
    private String genre;
    private int duration;
    private String rating;
    private String description;
    private String posterUrl;
}