package com.larr.movie_reservation_app.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlutterwaveResponse<T> {
    private String status;
    private String message;
    private T data;
}
