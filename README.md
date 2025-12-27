# Movie Reservation System

A Spring Boot 3 REST API for a comprehensive movie theater reservation platform. This system enables users to browse movies, view theater schedules, reserve seats, manage bookings, and complete payments for movie tickets.

## Project Overview

This is a full-featured movie reservation backend built with modern Java technologies. It provides APIs for user management, movie catalog, theater management, scheduling, ticket booking with seat locking mechanisms to prevent double-booking, and integrated payment processing via Flutterwave.

**Technology Stack**:

- **Framework**: Spring Boot 3.5.8 (Java 21, WAR packaging)
- **Authentication**: JWT (JSON Web Tokens) with Spring Security
- **Database**: PostgreSQL with Flyway migrations (V1-V16)
- **ORM**: Spring Data JPA
- **Payment Gateway**: Flutterwave (Cards & Mobile Money)
- **Utilities**: Lombok, Gson, Jackson

## Features

### 1. User Management

- User registration and login with JWT authentication
- Secure password hashing with jBcrypt
- User profile management (name, phone number, password updates)
- Role-based access control (`USER` and `ADMIN` roles)
- Session management via HTTP-only JWT cookies
- Flutterwave customer ID linking for payments

### 2. Movie Catalog

- Create, read, update, delete movies (admin only)
- Movie filtering and search by genre and title
- Pagination and sorting support for movie listings
- Movie status tracking: `COMING_SOON`, `NOW_SHOWING`, `ENDED`
- Active/inactive flag for movie availability
- Detailed movie information (title, genre, duration, rating, description, poster URL)

### 3. Theater Management

- Theater creation and management (admin only)
- Multiple screens per theater
- Theater activation/deactivation
- Pagination and sorting for theater listings
- Theater detail queries with address and city information

### 4. Screen Management

- Screen creation under theaters (admin only)
- Configurable seat layouts (rows and seats per row)
- Screen activation/deactivation
- Screen details with seat information
- List all screens by theater

### 5. Seat Management

- Automatic seat generation for screens with configurable base pricing
- Individual seat pricing (price stored per seat)
- Seat availability tracking via database function
- Unique seat numbering (row letter + number combination, e.g., A1, B5)
- Mark seats as out-of-service
- Bulk price updates per screen

### 6. Movie Scheduling

- Create and manage movie schedules for screens
- Schedule updates and modifications (admin only)
- List schedules by movie or screen
- Filter schedules by date with pagination
- Ticket pricing per schedule

### 7. Seat Locking

- Temporary seat reservation during booking process
- Automatic lock expiration (5 minutes)
- Lock status tracking per user and schedule
- Scheduled cleanup of expired locks (every 60 seconds)
- Prevents seat double-booking during payment

### 8. Ticket Booking

- Book multiple seats for a schedule
- Automatic total price calculation from seat prices
- Ticket status tracking: `PENDING`, `CONFIRMED`, `CANCELLED`
- Automatic cleanup of pending tickets after 7 minutes
- Ticket confirmation upon successful payment

### 9. Payment Integration (Flutterwave)

- **Card Payments**: Encrypted card processing with 3DS support
- **Mobile Money**: Support for multiple African networks (Ghana, Kenya, Uganda, Nigeria, Rwanda)
- **Payment Flow**:
  - Customer creation/retrieval
  - Payment method tokenization
  - Charge creation with redirect/OTP/PIN handling
  - Webhook processing for async payment confirmation
- **Webhook Verification**: HMAC signature validation for security
- **Multi-currency Support**: GHS, KES, UGX, NGN, RWF

## Database Schema

The system uses PostgreSQL with Flyway migrations (V1-V16). Key tables:

| Table | Description |
|-------|-------------|
| `users` | User accounts with authentication credentials, roles (USER/ADMIN), and Flutterwave customer ID |
| `theater` | Cinema locations with name, address, city, and active status |
| `screen` | Individual screens within theaters with row/seat configuration and active status |
| `seat` | Individual seats within screens with dynamic pricing and out-of-service flag |
| `movie` | Movie catalog with status tracking (COMING_SOON, NOW_SHOWING, ENDED) and active flag |
| `movie_schedule` | Scheduled movie showings with screen, time, and ticket prices |
| `ticket` | Purchased tickets with status tracking (PENDING, CONFIRMED, CANCELLED) |
| `ticket_seats` | Many-to-many relationship between tickets and seats |
| `seat_lock` | Temporary seat reservations during booking (5-minute auto-timeout) |

### Database Functions

- `available_seats_for_schedule(schedule_id)` - Returns seat availability for a schedule considering locks and confirmed tickets

### Key Indexes

- `idx_screen_theater_id` - Screen lookups by theater
- `idx_movie_schedule_movie_id` / `idx_movie_schedule_screen_id` - Schedule queries
- `idx_seat_screen_id` - Seat lookups by screen
- `idx_ticket_user_id` / `idx_ticket_schedule_id` - Ticket queries
- `idx_users_email` - User authentication
- `idx_seat_lock_schedule_seat` / `idx_seat_lock_locked_at` - Lock management

## Project Structure

```
src/main/java/com/larr/movie_reservation_app/
├── config/              # Configuration (Flutterwave, Security)
├── controller/          # REST endpoints
│   ├── MovieController.java
│   ├── MovieScheduleController.java
│   ├── PaymentWebhookController.java
│   ├── ScreenController.java
│   ├── SeatController.java
│   ├── TheaterController.java
│   ├── TicketController.java
│   └── UserController.java
├── dto/                 # Data transfer objects
│   ├── lock/            # Seat lock DTOs
│   ├── movieSchedule/   # Schedule DTOs
│   ├── payment/         # Payment DTOs (Card, Mobile, Webhook)
│   ├── screen/          # Screen DTOs
│   ├── seat/            # Seat DTOs (availability, creation)
│   ├── theater/         # Theater DTOs
│   └── user/            # User DTOs (login, register, update)
├── exception/           # Custom exceptions
├── model/               # JPA entities
│   ├── Movie.java
│   ├── MovieSchedule.java
│   ├── MovieStatus.java (enum: COMING_SOON, NOW_SHOWING, ENDED)
│   ├── Role.java (enum: USER, ADMIN)
│   ├── Screen.java
│   ├── Seat.java
│   ├── SeatLock.java
│   ├── Theater.java
│   ├── Ticket.java
│   └── TicketStatus.java (enum: PENDING, CONFIRMED, CANCELLED)
├── repository/          # Spring Data JPA repositories
├── security/            # JWT utilities and filters
│   ├── jwt/             # JWT token handling
│   └── service/         # Security services
├── service/             # Business logic
│   ├── mapper/          # Entity-DTO mappers
│   └── payment/         # Payment services
│       ├── EncryptionService.java
│       ├── FlutterwaveService.java
│       ├── TicketPaymentService.java
│       ├── TokenManager.java
│       └── WebhookVerificationService.java
└── ...

src/main/resources/
├── db/migration/        # Flyway SQL migrations (V1-V16)
└── application.properties
```

## Building and Running

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run

# Run tests
mvn test

# Run specific test class
mvn test -Dtest=ClassName
```

## API Documentation

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and receive JWT cookie |
| POST | `/api/auth/logout` | Logout and clear session |

### User Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users` | Get current user profile |
| PATCH | `/users/name` | Update user name |
| PATCH | `/users/phone` | Update phone number |
| PATCH | `/users/password` | Change password |

### Movie Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/api/movies/search` | Search/filter movies (pagination, sorting) | Public |
| GET | `/api/movies/{id}` | Get movie details | Public |
| POST | `/api/movies/` | Create movie | Admin |
| PUT | `/api/movies/{id}` | Update movie | Admin |
| DELETE | `/api/movies/{id}` | Delete movie | Admin |

**Query Parameters for Search**:
- `genre` - Filter by genre
- `title` - Filter by title
- `page` - Page number (default: 0)
- `size` - Page size (default: 10)
- `sortBy` - Sort field (default: title)

### Theater Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/api/theaters` | List all theaters (paginated) | Public |
| GET | `/api/theaters/active` | List active theaters (paginated) | Public |
| GET | `/api/theaters/{id}` | Get theater details | Public |
| POST | `/api/theaters` | Create theater | Admin |
| PUT | `/api/theaters/{id}` | Update theater | Admin |
| DELETE | `/api/theaters/{id}` | Delete theater | Admin |

### Screen Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/api/theaters/{theaterId}/screens` | List screens by theater | Admin |
| GET | `/api/theaters/{theaterId}/screens/{id}` | Get screen details | Public |
| POST | `/api/theaters/{theaterId}/screens` | Create screen | Admin |
| PUT | `/api/theaters/{theaterId}/screens/{id}` | Update screen | Admin |
| DELETE | `/api/theaters/{theaterId}/screens/{id}` | Delete screen | Admin |

### Seat Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/seats` | Generate seats for a screen | Admin |
| GET | `/api/screens/{screenId}/seats` | Get seats by screen | Public |
| PUT | `/api/screens/{screenId}/seats/base-price` | Update base price for all seats | Admin |
| PUT | `/api/seats/{id}/out-of-service` | Mark seat as out of service | Admin |
| POST | `/api/seats/lock-seat` | Lock a seat for booking | User |
| GET | `/api/schedules/{scheduleId}/available-seats` | Get available seats for schedule | Public |

### Schedule Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/api/schedules/{id}` | Get schedule details | Public |
| GET | `/api/movies/{movieId}/schedules` | List schedules for a movie | Public |
| GET | `/api/screens/{screenId}/schedules` | List schedules for a screen (by date) | Admin |
| POST | `/api/schedules` | Create schedule | Admin |
| PATCH | `/api/schedules/{id}` | Update schedule | Admin |

### Ticket Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/tickets/book` | Book seats (creates pending ticket) | User |
| POST | `/api/tickets/{ticketId}/pay/card` | Pay with card | User |

### Payment Webhook

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/webhooks/flutterwave` | Flutterwave payment webhook callback |

## Payment Flow

1. **Lock Seats**: User locks desired seats (`POST /api/seats/lock-seat`)
2. **Book Ticket**: Create pending ticket with locked seats (`POST /api/tickets/book`)
3. **Initiate Payment**: Submit payment details (`POST /api/tickets/{id}/pay/card`)
4. **Handle Response**:
   - `success`: Ticket confirmed immediately
   - `requires_redirect`: Redirect user to 3DS page
   - `requires_pin`: Prompt user for PIN
   - `requires_otp`: Prompt user for OTP
   - `pending`: Wait for webhook confirmation
5. **Webhook Confirmation**: Flutterwave sends webhook, ticket confirmed automatically

## Background Tasks

The application runs scheduled background tasks:

| Task | Interval | Description |
|------|----------|-------------|
| `SeatLockService.cleanUplocks()` | 60 seconds | Deletes seat locks older than 5 minutes |
| `TicketService.cleanUpTickets()` | 60 seconds | Cancels pending tickets older than 7 minutes |

## Configuration

Required environment variables / application properties:

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/movie_reservation
spring.datasource.username=
spring.datasource.password=

# JWT
jwt.secret=
jwt.expiration=86400000
jwt.cookieName=jwt

# Flutterwave
flutterwave.baseUrl=https://api.flutterwave.com/v3
flutterwave.secretKey=
flutterwave.clientId=
flutterwave.encryptionKey=
flutterwave.webhookSecret=
```

## Error Handling

Custom exceptions:
- `MovieNotFoundException` - Movie not found
- `TheaterNotFoundException` - Theater not found  
- `ScreenNotFoundException` - Screen not found
- `SeatNotFoundException` - Seat not found
- `TicketNotFoundException` - Ticket not found or status mismatch
- `LockedSeatException` - Seat already locked by another user

## Security

- JWT tokens stored in HTTP-only cookies
- Password hashing with BCrypt
- Role-based authorization (`@PreAuthorize`)
- Card data encrypted with AES-GCM before transmission
- Webhook signature verification (HMAC)
- No sensitive data exposed in API responses

## License

This project is open source and available under the MIT License.
