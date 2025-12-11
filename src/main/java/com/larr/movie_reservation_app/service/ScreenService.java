package com.larr.movie_reservation_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.dto.screen.ScreenDTO;
import com.larr.movie_reservation_app.dto.screen.ScreenDetailDTO;
import com.larr.movie_reservation_app.dto.screen.ScreenRequest;
import com.larr.movie_reservation_app.exception.ScreenNotFoundException;
import com.larr.movie_reservation_app.model.Screen;
import com.larr.movie_reservation_app.repository.ScreenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScreenService {
    private final ScreenRepository repository;

    public Screen findScreenById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ScreenNotFoundException("Screen with id: " + id + " not found"));
    }

    public Screen saveScreen(Screen screen) {
        return repository.save(screen);
    }

    public ScreenDetailDTO findScreenDetails(String id, String theaterId) {
        return repository.findScreenDetail(id, theaterId)
                .orElseThrow(() -> new ScreenNotFoundException(
                        "Screen with id: " + id + " and theater id: " + theaterId + " not found"));
    }

    public Screen updateScreen(String id, ScreenRequest request) {

        Screen screen = findScreenById(id);
        screen.setScreenNumber(request.screenNumber());
        screen.setTotalRows(request.totalRows());
        screen.setSeatsPerRow(request.seatsPerRow());

        return repository.save(screen);
    }

    public void deleteScreen(String id) {
        Screen screen = findScreenById(id);

        screen.setActive(false);

        repository.save(screen);
        return;
    }

    public List<ScreenDTO> listAllScreenByTheater(String theaterId) {
        return repository.listAllScreenByTheater(theaterId);
    }

}
