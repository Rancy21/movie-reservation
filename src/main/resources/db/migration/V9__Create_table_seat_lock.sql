CREATE TABLE IF NOT EXISTS seat_lock(
    id VARCHAR(36) PRIMARY KEY,
    seat_id VARCHAR(36),
    schedule_id VARCHAR(36),
    user_id VARCHAR(36),
    locked_at TIMESTAMP NOT NULL,
    FOREIGN KEY(seat_id) REFERENCES seat(id),
    FOREIGN KEY(schedule_id) REFERENCES movie_schedule(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);