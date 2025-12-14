package com.larr.movie_reservation_app.dto.movieSchedule;

import java.time.LocalDateTime;

public record MovieScheduleDetailDTO(
        String movie,
        String theater,
        int screenNumber,
        LocalDateTime date,
        double ticketPrice) {

}
