package com.larr.movie_reservation_app.service.mapper;

import com.larr.movie_reservation_app.dto.TheaterCreateRequest;
import com.larr.movie_reservation_app.dto.TheaterDTO;
import com.larr.movie_reservation_app.model.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapper {

    public TheaterDTO toDto(Theater theater) {
        if (theater == null) {
            return null;
        }

        String id = null;
        String name = null;
        String city = null;

        id = theater.getId();
        name = theater.getName();
        city = theater.getCity();

        TheaterDTO theaterDTO = new TheaterDTO(id, name, city);

        return theaterDTO;
    }

    public Theater toEntity(TheaterCreateRequest request) {
        if (request == null) {
            return null;
        }

        Theater theater = new Theater();

        theater.setName(request.name());
        theater.setAddress(request.address());
        theater.setCity(request.city());
        theater.setTotalScreens(request.totalScreens());

        return theater;
    }
}
