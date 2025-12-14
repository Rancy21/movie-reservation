package com.larr.movie_reservation_app.dto.movieSchedule;

import java.time.LocalDateTime;

public record MovieScheduleDTO(
                String id,
                String movie,
                int screenNumber,
                LocalDateTime date) {

}
