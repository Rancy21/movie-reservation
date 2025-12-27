package com.larr.movie_reservation_app.service.payment;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.larr.movie_reservation_app.dto.payment.ChargeRequest;
import com.larr.movie_reservation_app.dto.payment.CustomerRequest;
import com.larr.movie_reservation_app.dto.payment.FlutterwaveResponse;
import com.larr.movie_reservation_app.dto.payment.PaymentResponse;
import com.larr.movie_reservation_app.dto.payment.CustomerRequest.PhoneDTO;
import com.larr.movie_reservation_app.model.Ticket;
import com.larr.movie_reservation_app.model.TicketStatus;
import com.larr.movie_reservation_app.model.User;
import com.larr.movie_reservation_app.repository.TicketRepository;
import com.larr.movie_reservation_app.repository.UserRepository;
import com.larr.movie_reservation_app.service.TicketService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketPaymentService {
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;
    private final FlutterwaveService flutterwaveService;
    private final UserRepository userRepository;

    @SuppressWarnings("unchecked")
    // Process Card payment for a ticket
    @Transactional
    public PaymentResponse processCardPayment(String ticketId, String cardNumber, String expiryMonth, String expiryYear,
            String cvv) {
        Ticket ticket = ticketService.findTicket(ticketId, TicketStatus.PENDING);

        try {
            User user = ticket.getUser();
            // Create/Get customer
            if (user.getCustomerId() == null) {
                CustomerRequest customerReq = CustomerRequest.builder()
                        .email(user.getEmail())
                        .phone(PhoneDTO.builder()
                                .countryCode("250")
                                .number(user.getPhoneNumber())
                                .build())
                        .build();
                FlutterwaveResponse<?> customerResp = flutterwaveService.createCustomer(customerReq);
                String customerId = extractId(customerResp);
                user.setCustomerId(customerId);
                userRepository.save(user);
            }

            // Create card payment Method
            FlutterwaveResponse<?> paymentResponse = flutterwaveService.createCardPaymentMethod(cardNumber, expiryMonth,
                    expiryYear, cvv);
            String paymentMethodId = extractId(paymentResponse);

            // Create charge
            ChargeRequest chargeRequest = ChargeRequest.builder()
                    .reference(ticketId)
                    .currency("RWF")
                    .customerId(user.getCustomerId())
                    .amount(ticket.getTotalPrice())
                    .redirectUrl(" https://arrowy-daisy-officeless.ngrok-free.dev/callback")
                    .build();

            FlutterwaveResponse<?> chargeResponse = flutterwaveService.createCharge(chargeRequest);
            Map<String, Object> chargeData = (Map<String, Object>) chargeResponse.getData();
            String chargeId = (String) chargeData.get("id");
            String status = (String) chargeData.get("status");

            // Check if payment is immediate or needs authorization
            if ("succeeded".equals(status)) {
                // Payment succeeded immediately (rare for cards)
                ticketService.markTicketAsConfirmed(ticketId);
                return PaymentResponse.success(chargeId, "Payment successful");
            }

            // Handle authorization required
            Map<String, Object> nextAction = (Map<String, Object>) chargeData.get("next_action");
            String actionType = (String) nextAction.get("type");

            return buildPaymentResponse(actionType, chargeId, nextAction);

        } catch (Exception e) {
            throw new RuntimeException("Payment failed: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public PaymentResponse processMobileMoneyPayment(
            String ticketId,
            String network,
            String phoneNumber,
            String countryCode) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        try {
            User user = ticket.getUser();
            // Create/Get customer
            if (user.getCustomerId() == null) {
                CustomerRequest customerReq = CustomerRequest.builder()
                        .email(user.getEmail())
                        .phone(PhoneDTO.builder()
                                .countryCode("250")
                                .number(user.getPhoneNumber())
                                .build())
                        .build();
                FlutterwaveResponse<?> customerResp = flutterwaveService.createCustomer(customerReq);
                String customerId = extractId(customerResp);
                user.setCustomerId(customerId);
                userRepository.save(user);
            }

            // 2. Create mobile money payment method
            FlutterwaveResponse<?> pmResp = flutterwaveService.createMobilePaymentMethod(
                    countryCode, network, phoneNumber);
            String paymentMethodId = extractId(pmResp);

            // 3. Create charge
            ChargeRequest chargeReq = ChargeRequest.builder()
                    .reference(ticket.getId())
                    .currency(getCurrencyForCountry(countryCode))
                    .customerId(user.getCustomerId())
                    .paymentMethodId(paymentMethodId)
                    .amount(ticket.getTotalPrice())
                    .build();

            FlutterwaveResponse<?> chargeResp = flutterwaveService.createCharge(chargeReq);

            Map<String, Object> chargeData = (Map<String, Object>) chargeResp.getData();
            String chargeId = (String) chargeData.get("id");

            // Mobile money is always pending, user authorizes on phone
            Map<String, Object> nextAction = (Map<String, Object>) chargeData.get("next_action");
            String actionType = (String) nextAction.get("type");

            return buildPaymentResponse(actionType, chargeId, nextAction);

        } catch (Exception e) {
            throw new RuntimeException("Payment failed: " + e.getMessage());
        }
    }

    // Handle webhook from Flutterwave after payment

    @SuppressWarnings("unchecked")
    @Transactional
    public void handlePaymentWebhook(String ticketId, String chargeId, String status) {
        // Find ticket by charge ID
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if ("succeeded".equals(status)) {
            // Verify with Flutterwave before confirming
            FlutterwaveResponse<?> verification = flutterwaveService.verifyCharge(chargeId);

            Map<String, Object> data = (Map<String, Object>) verification.getData();
            String verifiedStatus = (String) data.get("status");
            Double amount = (Double) data.get("amount");

            // Double-check amount and status
            if ("succeeded".equals(verifiedStatus) &&
                    amount.equals(ticket.getTotalPrice())) {

                ticketService.markTicketAsConfirmed(ticket.getId());

                // Send confirmation email/SMS
                sendTicketConfirmation(ticket);
            }
        } else if ("failed".equals(status)) {
            ticketRepository.delete(ticket);
        }
    }

    @SuppressWarnings("unchecked")
    private PaymentResponse buildPaymentResponse(
            String actionType,
            String chargeId,
            Map<String, Object> nextAction) {

        switch (actionType) {
            case "redirect_url":
                Map<String, Object> redirectInfo = (Map<String, Object>) nextAction.get("redirect_url");
                String url = (String) redirectInfo.get("url");
                return PaymentResponse.requiresRedirect(chargeId, url);

            case "requires_pin":
                return PaymentResponse.requiresPin(chargeId);

            case "requires_otp":
                return PaymentResponse.requiresOtp(chargeId);

            case "payment_instruction":
                Map<String, Object> instruction = (Map<String, Object>) nextAction.get("payment_instruction");
                String note = (String) instruction.get("note");
                return PaymentResponse.pending(chargeId, note);

            default:
                return PaymentResponse.pending(chargeId, "Processing payment");
        }
    }

    @SuppressWarnings("unchecked")
    private String extractId(FlutterwaveResponse<?> response) {
        return ((Map<String, Object>) response.getData())
                .get("id").toString();
    }

    private String getCurrencyForCountry(String countryCode) {
        switch (countryCode) {
            case "233":
                return "GHS"; // Ghana
            case "254":
                return "KES"; // Kenya
            case "256":
                return "UGX"; // Uganda
            case "234":
                return "NGN"; // Nigeria
            case "250":
                return "RWF"; // Rwanda
            default:
                return "NGN";
        }
    }

    private void sendTicketConfirmation(Ticket ticket) {
        // Implement email/SMS notification
        System.out.println("Sending confirmation for ticket: " + ticket.getId());
    }
}
