package com.larr.movie_reservation_app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.dto.theater.TheaterDTO;
import com.larr.movie_reservation_app.dto.theater.TheaterDetailDTO;
import com.larr.movie_reservation_app.dto.theater.TheaterUpdateRequest;
import com.larr.movie_reservation_app.exception.TheaterNotFoundException;
import com.larr.movie_reservation_app.model.Theater;
import com.larr.movie_reservation_app.repository.TheaterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository repository;

    public Theater saveTheater(Theater theater) {
        return repository.save(theater);
    }

    public Theater findTheater(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new TheaterNotFoundException("Theater with Id: " + id + " not found"));
    }

    public Theater updateTheater(String id, TheaterUpdateRequest request) {
        Theater theater = findTheater(id);
        theater.setName(request.name());
        if (!request.city().isBlank())
            theater.setCity(request.city());
        if (!request.address().isBlank())
            theater.setAddress(request.address());
        theater.setTotalScreens(request.totalScreens());

        return repository.save(theater);
    }

    public void deleteTheater(String id) {
        Theater theater = findTheater(id);
        theater.setActive(false);
        repository.save(theater);
        return;
    }

    public TheaterDetailDTO findTheaterById(String id) {
        return repository.findByIdAsDto(id)
                .orElseThrow(() -> new TheaterNotFoundException("Theater with Id: " + id + " not found"));
    }

    public Page<TheaterDTO> listAllActiveTheaters(Pageable pageable) {
        return repository.listAllActive(pageable);
    }

    public Page<TheaterDTO> listAllTheater(Pageable pageable) {
        return repository.listAll(pageable);
    }
}