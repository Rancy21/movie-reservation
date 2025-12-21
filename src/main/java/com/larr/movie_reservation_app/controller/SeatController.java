package com.larr.movie_reservation_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.dto.lock.LockRequest;
import com.larr.movie_reservation_app.dto.seat.SeatCreateRequest;
import com.larr.movie_reservation_app.dto.seat.SeatUpdateRequest;
import com.larr.movie_reservation_app.model.SeatLock;
import com.larr.movie_reservation_app.service.SeatLockService;
import com.larr.movie_reservation_app.service.SeatService;
import com.larr.movie_reservation_app.service.mapper.SeatLockMapper;
import com.larr.movie_reservation_app.service.mapper.SeatMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SeatController {
    private final SeatService service;
    private final SeatMapper mapper;

    private final SeatLockService seatLockService;
    private final SeatLockMapper seatLockMapper;

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

    @PostMapping("api/seats/lock-seat")
    public ResponseEntity<?> lockSeat(@RequestBody LockRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        SeatLock savedLock = seatLockService.lockSeat(request.seatId(), request.scheduleId(),
                userDetails.getUsername());

        return ResponseEntity.ok(seatLockMapper.toDTO(savedLock));
    }

    @GetMapping("/api/schedules/{scheduleId}/available-seats")
    public ResponseEntity<?> findAvailableSeats(@PathVariable String scheduleId) {
        return ResponseEntity.ok(service.finSeatAvailableSeats(scheduleId));
    }
}
