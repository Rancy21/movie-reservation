CREATE INDEX if not exists idx_seat_lock_schedule on seat_lock(schedule_id);
CREATE UNIQUE INDEX if not exists idx_seat_lock_seat_schedule_unique on seat_lock(seat_id, schedule_id);