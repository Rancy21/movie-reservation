# Payment Webhook Implementation Summary

## Files Created

### 1. PaymentWebhookController.java
**Location**: `src/main/java/com/larr/movie_reservation_app/controller/PaymentWebhookController.java`

Main REST controller for handling Flutterwave webhook notifications:
- Endpoint: `POST /api/webhooks/flutterwave`
- Verifies webhook signature using HMAC SHA256
- Extracts payment data and delegates to TicketPaymentService
- Returns 200 on success, 401 on invalid signature, 500 on error

### 2. WebhookVerificationService.java
**Location**: `src/main/java/com/larr/movie_reservation_app/service/payment/WebhookVerificationService.java`

Handles cryptographic verification of webhook payloads:
- `verifyWebhookSignature()`: Main method for signature verification
- `hmacSha256()`: Generates HMAC SHA256 hash
- `constantTimeEquals()`: Constant-time string comparison (prevents timing attacks)
- Uses Flutterwave webhook secret from configuration

### 3. WebhookPayload.java
**Location**: `src/main/java/com/larr/movie_reservation_app/dto/payment/WebhookPayload.java`

Data Transfer Object for webhook payload structure:
- Contains event type and nested WebhookData
- Fields: id, status, reference, amount, currency, customerId
- Provides type safety for webhook data

### 4. PAYMENT_WEBHOOK_SETUP.md
**Location**: `docs/PAYMENT_WEBHOOK_SETUP.md`

Comprehensive documentation covering:
- Webhook overview and architecture
- Endpoint details and payload structure
- Security configuration and signature verification
- Flutterwave dashboard setup instructions
- Local testing with ngrok
- Payment flow diagram
- Error handling and database state changes

## Integration Points

### Existing Dependencies Used
- **TicketPaymentService**: `handlePaymentWebhook()` already exists and processes payment status
- **FlutterwaveConfig**: Provides webhook secret for signature verification
- **Ticket Model**: Updated status based on payment outcome

### Workflow
1. Flutterwave sends POST to `/api/webhooks/flutterwave`
2. Controller extracts signature from `X-Webhook-Signature` header
3. WebhookVerificationService verifies HMAC SHA256 signature
4. PaymentWebhookController parses payload and extracts: chargeId, status, reference (ticketId)
5. Delegates to `TicketPaymentService.handlePaymentWebhook()`
6. Service updates ticket status based on payment status:
   - **succeeded**: Mark ticket as CONFIRMED
   - **failed**: Delete ticket and release seats
   - **pending**: Keep ticket in PENDING state

## Security Features

- **HMAC SHA256 Signature Verification**: Validates webhook authenticity
- **Constant-Time Comparison**: Prevents timing attacks on signature validation
- **Webhook Secret Configuration**: Stored in application properties
- **Error Handling**: Graceful error responses without exposing internal details

## Configuration Required

Add to `application.properties` or environment variables:
```properties
flutterwave.webhookSecret=<get-from-flutterwave-dashboard>
```

## Testing

### Local Testing with ngrok
1. Run application: `mvn spring-boot:run`
2. Expose via ngrok: `ngrok http 8080`
3. Add webhook in Flutterwave dashboard: `https://xxxxx.ngrok-free.dev/api/webhooks/flutterwave`

### Manual Testing
See PAYMENT_WEBHOOK_SETUP.md for curl examples and mock payload structure

## No Modifications to Existing Code

All implementation is backward-compatible:
- No changes to existing controllers or services
- TicketPaymentService.handlePaymentWebhook() already existed and was utilized
- Existing payment endpoints remain unchanged
- Database schema not modified
