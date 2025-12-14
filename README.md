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
- Movie status tracking (ACTIVE, INACTIVE, ARCHIVED)
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
- Different seat types (STANDARD, PREMIUM, VIP)
- Variable pricing based on seat type
- Seat availability tracking
- Unique seat numbering (row + number combination)

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

## Pending Features

### 1. Seat Booking Process
- **Endpoints to implement**:
  - `POST /api/tickets/lock-seats` - Lock multiple seats for a user during booking
  - `POST /api/tickets/unlock-seats` - Unlock seats (timeout or user cancellation)
  - `GET /api/schedules/{scheduleId}/available-seats` - Get available seats for a schedule
  - `GET /api/tickets/{scheduleId}/locked-seats` - View currently locked seats

- **Service Methods**:
  - `lockSeatsForBooking()` - Create seat locks with expiration
  - `unlockSeatsOnTimeout()` - Expire locks automatically
  - `getAvailableSeats()` - Calculate and return available seats
  - `areSeatsAvailable()` - Check seat availability

### 2. Seat Locking Process
- **Endpoints to implement**:
  - `PATCH /api/seat-locks/{lockId}/extend` - Extend lock timeout
  - `DELETE /api/seat-locks/{lockId}` - Manually unlock a seat

- **Service Methods**:
  - `extendLockTimeout()` - Extend lock expiration
  - `isLockExpired()` - Check lock validity (already in SeatLock model)
  - `cleanupExpiredLocks()` - Scheduled task to remove expired locks
  - `lockSeat()` - Create individual seat lock

### 3. Ticket Purchasing & Payment Integration
- **Endpoints to implement**:
  - `POST /api/tickets` - Create ticket from locked seats
  - `GET /api/tickets/{id}` - Get ticket details
  - `GET /api/users/tickets` - List user's tickets
  - `DELETE /api/tickets/{id}` - Cancel ticket
  - `PATCH /api/tickets/{id}/status` - Update ticket status
  - `POST /api/payments/initiate` - Initiate payment process
  - `POST /api/payments/confirm` - Confirm payment and finalize ticket
  - `POST /api/payments/callback` - Handle payment gateway callbacks

- **Service Methods**:
  - `createTicket()` - Convert locked seats to ticket
  - `calculateTotalPrice()` - Sum seat prices
  - `updateTicketStatus()` - Update ticket status
  - Payment processing and integration
  - Transaction management and rollback

- **Payment Gateway Integration** (Recommended):
  - PayPal integration (infrastructure in place, Gson dependency added)
  - Stripe integration
  - Support for multiple payment methods
  - Payment confirmation and webhook handling
  - Transaction logging and audit trail

## Database Schema

The system uses PostgreSQL with the following key tables:

- **users** - User accounts with authentication credentials
- **theater** - Cinema locations
- **screen** - Individual screens within theaters
- **seat** - Individual seats within screens
- **movie** - Movie catalog
- **movie_schedule** - Scheduled movie showings
- **ticket** - Purchased tickets
- **seat_lock** - Temporary seat reservations during booking
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

## Development Notes

- Use `jakarta.*` imports (not `javax.*`) for Spring Boot 3+
- Organize imports alphabetically
- Follow the established naming conventions (PascalCase for classes, camelCase for methods, snake_case for database columns)
- Use Lombok `@Data` annotation for entity classes
- Return DTOs from controllers, not entities
- Extract authenticated user from `SecurityContextHolder` when needed
- All database operations use UUID primary keys

## License

This project is open source and available under the MIT License.
