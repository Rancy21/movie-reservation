package com.larr.movie_reservation_app.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.dto.TheaterCreateRequest;
import com.larr.movie_reservation_app.dto.TheaterUpdateRequest;
import com.larr.movie_reservation_app.model.Theater;
import com.larr.movie_reservation_app.service.TheaterService;
import com.larr.movie_reservation_app.service.mapper.TheaterMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/theaters")
public class TheaterController {
    private final TheaterService service;
    private final TheaterMapper theaterMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTheater(@PathVariable String id) {
        return ResponseEntity.ok(service.findTheaterById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> saveTheater(@RequestBody TheaterCreateRequest request) {
        Theater theater = theaterMapper.toEntity(request);

        return ResponseEntity.ok(theaterMapper.toDto(service.saveTheater(theater)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTheater(@PathVariable String id, @RequestBody TheaterUpdateRequest request) {
        return ResponseEntity.ok(theaterMapper.toDto(service.updateTheater(id, request)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheater(@PathVariable String id) {
        service.deleteTheater(id);
        return ResponseEntity.ok("Theater deleted successfully");
    }

    @GetMapping("/active")
    public ResponseEntity<?> listAllActiveTheaters(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "0") int page

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return ResponseEntity.ok(service.listAllActiveTheaters(pageable));
    }

    @GetMapping()
    public ResponseEntity<?> listAllTheaters(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "0") int page

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return ResponseEntity.ok(service.listAllTheater(pageable));
    }

}
