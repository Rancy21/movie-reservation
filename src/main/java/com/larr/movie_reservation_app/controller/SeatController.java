package com.larr.movie_reservation_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.dto.seat.SeatCreateRequest;
import com.larr.movie_reservation_app.dto.seat.SeatUpdateRequest;
import com.larr.movie_reservation_app.service.SeatService;
import com.larr.movie_reservation_app.service.mapper.SeatMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SeatController {
    private final SeatService service;
    private final SeatMapper mapper;

    @PostMapping("/api/seats")
    public ResponseEntity<?> generateSeats(@RequestBody SeatCreateRequest request) {
        return ResponseEntity.ok(service.generateSeatsForScreen(request.screenId(), request.basePrice()));
    }

    @PutMapping("/api/screens/{screenId}/seats/base-price")
    public ResponseEntity<?> updateBasePrice(@PathVariable String screenId, @RequestBody SeatUpdateRequest request) {
        return ResponseEntity.ok(service.updateBasePrice(screenId, request.newPrice()));
    }

    @PutMapping("/api/seats/{id}/out-of-service")
    public ResponseEntity<?> setSeatOutOfService(@PathVariable String id) {
        return ResponseEntity.ok(mapper.toSeatDTO(service.setSeatOutOfService(id)));
    }

    @GetMapping("/api/screens/{screenId}/seats")
    public ResponseEntity<?> getSeatsByScreen(@PathVariable String screenId) {
        return ResponseEntity.ok(service.findSeatsByScreen(screenId));
    }

}
