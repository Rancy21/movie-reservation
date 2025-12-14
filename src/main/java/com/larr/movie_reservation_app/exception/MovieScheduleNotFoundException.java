package com.larr.movie_reservation_app.exception;

public class MovieScheduleNotFoundException extends RuntimeException {
    public MovieScheduleNotFoundException(String message) {
        super(message);
    }
}
