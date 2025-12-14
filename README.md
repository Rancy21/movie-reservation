# Movie Reservation System

A Spring Boot 3 REST API for a comprehensive movie theater reservation platform. This system enables users to browse movies, view theater schedules, reserve seats, manage bookings, and complete payments for movie tickets.

## Project Overview

This is a full-featured movie reservation backend built with modern Java technologies. It provides APIs for user management, movie catalog, theater management, scheduling, and ticket booking with seat locking mechanisms to prevent double-booking.

**Technology Stack**:

- **Framework**: Spring Boot 3.5.8 (Java 21)
- **Authentication**: JWT (JSON Web Tokens) with Spring Security
- **Database**: PostgreSQL with Flyway migrations
- **ORM**: Spring Data JPA
- **Utilities**: Lombok, Gson

## Completed Features

### 1. User Management

- User registration and login with JWT authentication
- Secure password hashing with jBcrypt
- User profile management (name, phone number, password updates)
- Role-based access control (USER and ADMIN roles)
- Session management via HTTP-only JWT cookies

### 2. Movie Catalog

- Create, read, update, delete movies (admin only)
- Movie filtering and search by genre and title
- Pagination support for movie listings
- Movie status tracking (COMING_SOON, NOW_SHOWING, ENDED)
- Active/inactive flag for movie availability
- Detailed movie information (title, genre, duration, rating, description, poster URL)

### 3. Theater Management

- Theater creation and management (admin only)
- Multiple screens per theater
- Theater activation/deactivation
- Theater detail queries

### 4. Screen Management

- Screen creation under theaters (admin only)
- Configurable seat layouts (rows and seats per row)
- Screen activation/deactivation
- Screen details with seat information

### 5. Seat Management

- Automatic seat generation for screens
- Individual seat pricing (price stored per seat)
- Seat availability tracking
- Unique seat numbering (row + number combination)
- Previously stored seat types (removed in V11 migration)

### 6. Movie Scheduling

- Create and manage movie schedules for screens
- Schedule updates and modifications
- List schedules by movie or screen
- Filter schedules by date
- Pagination for schedule listings
- Automatic seat locking infrastructure

### 7. Seat Locking (Infrastructure)

- Seat lock entity and repository setup
- Automatic lock timeout mechanism (10 minutes)
- Lock status tracking per user and schedule
- Prevents seat double-booking during booking process

## Pending Features & Infrastructure

### 1. Seat Locking Infrastructure (Database Schema Ready)

- **Implemented**:

  - `SeatLock` entity with automatic lock timeout (10 minutes)
  - `seat_lock` table with proper indexing (V10 migration)
  - Lock expiration validation method: `isLockExpired()`
  - Locked seat tracking (seat, user, schedule, timestamp)

- **Endpoints to implement**:

  - `POST /api/tickets/lock-seats` - Lock multiple seats for a user during booking
  - `POST /api/tickets/unlock-seats` - Unlock seats (timeout or user cancellation)
  - `PATCH /api/seat-locks/{lockId}/extend` - Extend lock timeout
  - `DELETE /api/seat-locks/{lockId}` - Manually unlock a seat
  - `GET /api/schedules/{scheduleId}/available-seats` - Get available seats for a schedule
  - `GET /api/tickets/{scheduleId}/locked-seats` - View currently locked seats

- **Service Methods to implement**:
  - `lockSeatsForBooking()` - Create seat locks with expiration
  - `unlockSeatsOnTimeout()` - Expire locks automatically
  - `getAvailableSeats()` - Calculate and return available seats
  - `areSeatsAvailable()` - Check seat availability
  - `extendLockTimeout()` - Extend lock expiration
  - `cleanupExpiredLocks()` - Scheduled task to remove expired locks
  - `lockSeat()` - Create individual seat lock

### 2. Ticket Service & Repositories

- **Implemented**:

  - `Ticket` entity with status tracking (TicketStatus enum with PENDING status)
  - `ticket_seats` many-to-many join table
  - `TicketRepository` (basic JPA repository)

- **Endpoints to implement**:

  - `POST /api/tickets` - Create ticket from locked seats
  - `GET /api/tickets/{id}` - Get ticket details
  - `GET /api/users/tickets` - List user's tickets
  - `DELETE /api/tickets/{id}` - Cancel ticket
  - `PATCH /api/tickets/{id}/status` - Update ticket status

- **Service Methods to implement**:
  - `createTicket()` - Convert locked seats to ticket
  - `calculateTotalPrice()` - Sum seat prices
  - `updateTicketStatus()` - Update ticket status
  - `getUserTickets()` - Retrieve user's ticket history
  - `cancelTicket()` - Cancel and refund tickets

### 3. Payment Gateway Integration

- **Dependencies**:

  - Gson (already added for JSON serialization)
  - JWT for secure token handling

- **Endpoints to implement**:

  - `POST /api/payments/initiate` - Initiate payment process
  - `POST /api/payments/confirm` - Confirm payment and finalize ticket

- **Suggested integrations**:
  - Fluttewave integration

### 4. Missing Repository

- Need to create `SeatLockRepository` extending `JpaRepository<SeatLock, String>`

## Database Schema

The system uses PostgreSQL with Flyway migrations (V1-V11). Key tables:

- **users** - User accounts with authentication credentials and roles (USER/ADMIN)
- **theater** - Cinema locations with active status
- **screen** - Individual screens within theaters with active status
- **seat** - Individual seats within screens with dynamic pricing
- **movie** - Movie catalog with status tracking (COMING_SOON, NOW_SHOWING, ENDED) and active flag
- **movie_schedule** - Scheduled movie showings with ticket prices
- **ticket** - Purchased tickets with status tracking (PENDING, CONFIRMED, CANCELLED)
- **seat_lock** - Temporary seat reservations during booking (10-minute auto-timeout)
- **ticket_seats** - Many-to-many relationship between tickets and seats

## Project Structure

```
src/main/java/com/larr/movie_reservation_app/
├── controller/          # REST endpoints
├── service/             # Business logic
├── model/               # JPA entities
├── repository/          # Data access layer
├── dto/                 # Data transfer objects
├── security/            # JWT and authentication
├── exception/           # Custom exceptions
└── ...

src/main/resources/
└── db/migration/        # Flyway database migrations
```

## Building and Running

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run

# Run tests
mvn test
```

## API Documentation

### Authentication

- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login and receive JWT token
- `POST /api/auth/logout` - Logout and clear session

### User Endpoints

- `GET /users` - Get current user profile
- `PATCH /users/name` - Update user name
- `PATCH /users/phone` - Update phone number
- `PATCH /users/password` - Change password

### Movie Endpoints

- `GET /api/movies/search` - Search and filter movies
- `GET /api/movies/{id}` - Get movie details
- `POST /api/movies/` - Create movie (admin)
- `PUT /api/movies/{id}` - Update movie (admin)
- `DELETE /api/movies/{id}` - Delete movie (admin)

### Theater Endpoints

- `GET /api/theaters` - List all theaters
- `GET /api/theaters/{id}` - Get theater details
- `POST /api/theaters` - Create theater (admin)
- `PUT /api/theaters/{id}` - Update theater (admin)
- `DELETE /api/theaters/{id}` - Delete theater (admin)

### Screen Endpoints

- `GET /api/screens/{id}` - Get screen details
- `POST /api/screens` - Create screen (admin)
- `PUT /api/screens/{id}` - Update screen (admin)

### Schedule Endpoints

- `GET /api/schedules/{id}` - Get schedule details
- `GET /api/movies/{movieId}/schedules` - List schedules for a movie
- `GET /api/screens/{screenId}/schedules` - List schedules for a screen
- `POST /api/schedules` - Create schedule (admin)
- `PATCH /api/schedules/{id}` - Update schedule (admin)

## License

This project is open source and available under the MIT License.
