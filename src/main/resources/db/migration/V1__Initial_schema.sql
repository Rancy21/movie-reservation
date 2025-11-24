-- Create users table
CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Create theater table
CREATE TABLE theater (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(100),
    total_screens INT NOT NULL
);

-- Create movie table
CREATE TABLE movie (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    duration INT NOT NULL,
    rating VARCHAR(10),
    description TEXT,
    poster_url VARCHAR(500)
);

-- Create screen table
CREATE TABLE screen (
    id VARCHAR(36) PRIMARY KEY,
    theater_id VARCHAR(36) NOT NULL,
    screen_number INT NOT NULL,
    total_rows INT NOT NULL,
    seats_per_row INT NOT NULL,
    FOREIGN KEY (theater_id) REFERENCES theater(id)
);

-- Create movie_schedule table
CREATE TABLE movie_schedule (
    id VARCHAR(36) PRIMARY KEY,
    movie_id VARCHAR(36) NOT NULL,
    screen_id VARCHAR(36) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    ticket_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (screen_id) REFERENCES screen(id)
);

-- Create seat table
CREATE TABLE seat (
    id VARCHAR(36) PRIMARY KEY,
    screen_id VARCHAR(36) NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    row VARCHAR(10) NOT NULL,
    number INT NOT NULL,
    type VARCHAR(20) NOT NULL,
    FOREIGN KEY (screen_id) REFERENCES screen(id),
    UNIQUE (screen_id, seat_number)
);

-- Create ticket table
CREATE TABLE ticket (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    schedule_id VARCHAR(36) NOT NULL,
    booking_time TIMESTAMP NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (schedule_id) REFERENCES movie_schedule(id)
);

-- Create ticket_seats join table (many-to-many between ticket and seat)
CREATE TABLE ticket_seats (
    ticket_id VARCHAR(36) NOT NULL,
    seat_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (ticket_id, seat_id),
    FOREIGN KEY (ticket_id) REFERENCES ticket(id),
    FOREIGN KEY (seat_id) REFERENCES seat(id)
);

-- Create indexes for better query performance
CREATE INDEX idx_screen_theater_id ON screen(theater_id);
CREATE INDEX idx_movie_schedule_movie_id ON movie_schedule(movie_id);
CREATE INDEX idx_movie_schedule_screen_id ON movie_schedule(screen_id);
CREATE INDEX idx_seat_screen_id ON seat(screen_id);
CREATE INDEX idx_ticket_user_id ON ticket(user_id);
CREATE INDEX idx_ticket_schedule_id ON ticket(schedule_id);
CREATE INDEX idx_users_email ON users(email);
