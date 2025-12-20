package com.larr.movie_reservation_app.exception;

public class LockedSeatException extends RuntimeException {
    public LockedSeatException(String message) {
        super(message);
    }
}
