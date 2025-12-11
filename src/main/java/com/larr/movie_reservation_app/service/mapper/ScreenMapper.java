package com.larr.movie_reservation_app.service.mapper;

import org.springframework.stereotype.Component;

import com.larr.movie_reservation_app.dto.screen.ScreenDTO;
import com.larr.movie_reservation_app.dto.screen.ScreenRequest;
import com.larr.movie_reservation_app.model.Screen;

@Component
public class ScreenMapper {

    public ScreenDTO toDto(Screen screen) {
        if (screen == null) {
            return null;
        }

        ScreenDTO screenDTO = new ScreenDTO(screen.getId(), screen.getTheater().getName(), screen.getScreenNumber(),
                screen.isActive());

        return screenDTO;
    }

    public Screen toScreen(ScreenRequest request) {
        if (request == null) {
            return null;
        }

        Screen screen = new Screen();
        screen.setScreenNumber(request.screenNumber());
        screen.setTotalRows(request.totalRows());
        screen.setSeatsPerRow(request.seatsPerRow());

        return screen;
    }
}
