package com.larr.movie_reservation_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.dto.screen.ScreenRequest;
import com.larr.movie_reservation_app.model.Screen;
import com.larr.movie_reservation_app.model.Theater;
import com.larr.movie_reservation_app.service.ScreenService;
import com.larr.movie_reservation_app.service.TheaterService;
import com.larr.movie_reservation_app.service.mapper.ScreenMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/theaters/{theaterId}/screens")
public class ScreenController {
    private final ScreenService service;
    private final TheaterService theaterService;
    private final ScreenMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getScreen(@PathVariable String theaterId, @PathVariable String id) {
        return ResponseEntity.ok(service.findScreenDetails(id, theaterId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveScreen(@PathVariable String theaterId, @RequestBody ScreenRequest request) {
        Screen screen = mapper.toScreen(request);
        Theater theater = theaterService.findTheater(theaterId);

        screen.setTheater(theater);

        return ResponseEntity.ok(mapper.toDto(service.saveScreen(screen)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScreen(@PathVariable String id, @RequestBody ScreenRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.updateScreen(id, request)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> listAllScreensByTheater(@PathVariable String theaterId) {
        return ResponseEntity.ok(service.listAllScreenByTheater(theaterId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable String id) {
        service.deleteScreen(id);

        return ResponseEntity.ok("Screen deleted successfully");
    }

}
