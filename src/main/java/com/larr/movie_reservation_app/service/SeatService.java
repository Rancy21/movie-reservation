package com.larr.movie_reservation_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.dto.seat.SeatDTO;
import com.larr.movie_reservation_app.exception.SeatNotFoundException;
import com.larr.movie_reservation_app.model.Screen;
import com.larr.movie_reservation_app.model.Seat;
import com.larr.movie_reservation_app.repository.SeatRepository;
import com.larr.movie_reservation_app.service.mapper.SeatMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository repository;
    private final ScreenService screenService;
    private final SeatMapper mapper;

    public Seat findSeatById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new SeatNotFoundException("Seat with id:" + id + " not found"));
    }

    public List<SeatDTO> generateSeatsForScreen(String screenId, Double basePrice) {
        List<Seat> seats = new ArrayList<>();
        Screen screen = screenService.findScreenById(screenId);
        for (int row = 0; row < screen.getTotalRows(); row++) {
            for (int number = 1; number <= screen.getSeatsPerRow(); number++) {
                Seat seat = new Seat();
                seat.setScreen(screen);
                seat.setRow(String.valueOf((char) ('A' + row)));
                seat.setNumber(number);
                seat.setSeatNumber(seat.getRow() + seat.getNumber());
                seat.setPrice(basePrice);
                seats.add(seat);
            }
        }

        return repository.saveAll(seats).stream().map(mapper::toSeatDTO).collect(Collectors.toList());

    }

    public List<SeatDTO> updateBasePrice(String screenId, Double newPrice) {
        List<Seat> seats = repository.findByScreen(screenId);

        for (Seat seat : seats) {
            seat.setPrice(newPrice);
        }

        return repository.saveAll(seats).stream().map(mapper::toSeatDTO).collect(Collectors.toList());
    }

    public Seat setSeatOutOfService(String seatId) {
        Seat seat = findSeatById(seatId);

        seat.setOutOfService(true);

        return repository.save(seat);
    }

    public List<SeatDTO> findSeatsByScreen(String screenId) {
        return repository.findSeatsByScreen(screenId);
    }

}
