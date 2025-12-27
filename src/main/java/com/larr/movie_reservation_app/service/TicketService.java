package com.larr.movie_reservation_app.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.exception.TicketNotFoundException;
import com.larr.movie_reservation_app.model.MovieSchedule;
import com.larr.movie_reservation_app.model.Seat;
import com.larr.movie_reservation_app.model.Ticket;
import com.larr.movie_reservation_app.model.TicketStatus;
import com.larr.movie_reservation_app.model.User;
import com.larr.movie_reservation_app.repository.SeatRepository;
import com.larr.movie_reservation_app.repository.TicketRepository;
import com.larr.movie_reservation_app.security.service.MovieScheduleService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository repository;
    private final SeatRepository seatRepository;
    private final MovieScheduleService scheduleService;
    private final UserService userService;

    public Ticket saveTicket(String email, String scheduleId, List<String> seatIds) {
        User user = userService.getUserByEmail(email);
        MovieSchedule schedule = scheduleService.findScheduleById(scheduleId);
        List<Seat> seats = seatRepository.findAllById(seatIds);
        double totalPrice = seats.stream().mapToDouble(Seat::getPrice).sum();

        Ticket ticket = Ticket.builder().user(user)
                .schedule(schedule)
                .seats(seats)
                .totalPrice(totalPrice)
                .status(TicketStatus.PENDING).build();

        return repository.save(ticket);
    }

    public Ticket findTicket(String id, TicketStatus status) {
        return repository.findTicketByIdAndStatus(id, status).orElseThrow(() -> new TicketNotFoundException(
                "ticket with id: " + id + " and status: " + status.toString() + " not found"));
    }

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void cleanUpTickets() {
        repository.cleanupTickets();
    }

    public void markTicketAsConfirmed(String id) {
        Ticket ticket = findTicket(id, TicketStatus.PENDING);

        ticket.setStatus(TicketStatus.CONFIRMED);

        repository.save(ticket);
    }

}
