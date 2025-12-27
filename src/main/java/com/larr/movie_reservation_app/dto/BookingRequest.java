package com.larr.movie_reservation_app.dto;

import java.util.List;

public record BookingRequest(String userId, String scheduleId, List<String> seatIds) {

}
