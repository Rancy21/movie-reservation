# Payment Webhook Setup Guide

## Overview

The payment webhook system handles asynchronous payment confirmations from Flutterwave. When a customer completes a payment, Flutterwave sends a POST request to your webhook endpoint to confirm the transaction status.

## Webhook Endpoint

**URL**: `POST /api/webhooks/flutterwave`

**Base URL**: `https://yourdomain.com/api/webhooks/flutterwave`

## Webhook Payload Structure

Flutterwave sends JSON payloads with the following structure:

```json
{
  "event": "charge.completed",
  "data": {
    "id": "charge-id-12345",
    "status": "succeeded",
    "reference": "ticket-id",
    "amount": 25000,
    "currency": "RWF",
    "customerId": "customer-id"
  }
}
```

### Data Fields
- `id`: The charge ID from Flutterwave
- `status`: Payment status - `succeeded`, `failed`, or `pending`
- `reference`: The ticket ID (matches the ticket being paid for)
- `amount`: Transaction amount in smallest currency unit
- `currency`: Currency code (e.g., RWF, UGX, KES, GHS, NGN)
- `customerId`: Flutterwave customer ID

## Security: Webhook Signature Verification

All webhooks include an `X-Webhook-Signature` header containing an HMAC SHA256 signature.

**Header**: `X-Webhook-Signature: <hex-encoded-hmac>`

### Signature Verification Process

1. Extract the `X-Webhook-Signature` header
2. Serialize the JSON payload
3. Generate HMAC SHA256 using:
   - Message: The JSON payload string
   - Secret: Your Flutterwave webhook secret
4. Compare the calculated signature with the header value (constant-time comparison)

The application automatically verifies this signature. If verification fails, a 401 Unauthorized response is returned.

## Configuration

Add these environment variables to your `.env` or `application.properties`:

```properties
flutterwave.baseUrl=https://api.flutterwave.com/v3
flutterwave.clientId=<your-client-id>
flutterwave.clientSecret=<your-client-secret>
flutterwave.encryptionKey=<your-encryption-key>
flutterwave.webhookSecret=<your-webhook-secret>
```

**Important**: Get your webhook secret from the Flutterwave dashboard under Settings → Webhooks.

## Setting Up Webhook in Flutterwave Dashboard

1. Log in to Flutterwave Dashboard
2. Go to Settings → Webhooks
3. Add webhook URL: `https://yourdomain.com/api/webhooks/flutterwave`
4. Select events: Enable "charge.completed" events
5. Copy and save the webhook secret

## Payment Flow

```
1. User initiates payment via POST /api/tickets/{ticketId}/pay/card
2. Application creates charge with Flutterwave
3. User completes payment (card auth, 3D Secure, etc.)
4. Flutterwave sends webhook to POST /api/webhooks/flutterwave
5. Application verifies signature and processes payment
6. If succeeded: Mark ticket as confirmed, send confirmation email
7. If failed: Delete ticket and notify user
```

## Response Codes

| Code | Meaning |
|------|---------|
| 200 | Webhook processed successfully |
| 401 | Invalid webhook signature |
| 500 | Error processing webhook |

The application always returns 200 to prevent Flutterwave from retrying indefinitely (even if there was an error internally).

## Testing Webhook Locally

### Using ngrok for local testing:

1. Start your application: `mvn spring-boot:run`
2. Expose port 8080: `ngrok http 8080`
3. Use ngrok URL in Flutterwave dashboard: `https://xxxxx.ngrok-free.dev/api/webhooks/flutterwave`

### Mock webhook request:

```bash
curl -X POST http://localhost:8080/api/webhooks/flutterwave \
  -H "Content-Type: application/json" \
  -H "X-Webhook-Signature: test-signature" \
  -d '{
    "event": "charge.completed",
    "data": {
      "id": "charge-123",
      "status": "succeeded",
      "reference": "ticket-id",
      "amount": 25000,
      "currency": "RWF",
      "customerId": "cust-123"
    }
  }'
```

## Classes Involved

- **PaymentWebhookController**: Main webhook endpoint
- **WebhookVerificationService**: Handles HMAC signature verification
- **TicketPaymentService.handlePaymentWebhook()**: Processes payment status
- **WebhookPayload**: DTO for webhook payload

## Error Handling

The webhook handler:
- Logs all webhook events
- Returns descriptive error messages
- Handles signature verification failures
- Gracefully handles missing ticket or charge records

## Database State After Webhook

### If status = "succeeded":
- Ticket marked as CONFIRMED
- Ticket updated with payment date
- Confirmation email sent to user

### If status = "failed":
- Ticket deleted from database
- User notified of payment failure
- Seats released for other users

### If status = "pending":
- Ticket remains in PENDING state
- User guided to complete payment
