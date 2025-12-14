package com.larr.movie_reservation_app.service.mapper;

import org.springframework.stereotype.Component;

import com.larr.movie_reservation_app.dto.movieSchedule.MovieScheduleDTO;
import com.larr.movie_reservation_app.model.MovieSchedule;

@Component
public class MovieScheduleMapper {
    public MovieScheduleDTO toMovieScheduleDTO(MovieSchedule schedule) {

        return new MovieScheduleDTO(
                schedule.getId(),
                schedule.getMovie().getTitle(),
                schedule.getScreen().getScreenNumber(),
                schedule.getStartTime());
    }
}
