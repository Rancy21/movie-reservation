package com.larr.movie_reservation_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larr.movie_reservation_app.dto.BookingRequest;
import com.larr.movie_reservation_app.dto.payment.CardDetails;
import com.larr.movie_reservation_app.dto.payment.PaymentResponse;
import com.larr.movie_reservation_app.model.Ticket;
import com.larr.movie_reservation_app.service.TicketService;
import com.larr.movie_reservation_app.service.payment.TicketPaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TicketPaymentService paymentService;

    @PostMapping("/book")
    public ResponseEntity<Ticket> bookSeats(@RequestBody BookingRequest request) {
        String userId = request.userId();
        String scheduleId = request.scheduleId();

        List<String> seatIds = request.seatIds();

        Ticket ticket = ticketService.saveTicket(userId, scheduleId, seatIds);

        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/{ticketId}/pay/card")
    public ResponseEntity<PaymentResponse> payWithCard(@PathVariable String ticketId,
            @RequestBody CardDetails cardDetails) {
        PaymentResponse response = paymentService.processCardPayment(ticketId,
                cardDetails.cardNumber(),
                cardDetails.expiryMonth(),
                cardDetails.expiryYear(),
                cardDetails.cvv());

        return ResponseEntity.ok(response);
    }

}
