CREATE OR REPLACE FUNCTION available_seats_for_schedule(p_schedule_id varchar(36)) RETURNS TABLE (
        seat_id varchar(36),
        seat_number varchar(10),
        seat_row char(10),
        number integer,
        price numeric(10, 2),
        is_available boolean
    ) AS $$
SELECT s.id AS seat_id,
    s.seat_number AS seat_number,
    s.row AS seat_row,
    s.number AS number,
    s.price AS price,
    (
        s.is_out_of_service = false
        AND sl.seat_id IS NULL
        AND ts.seat_id IS NULL
    ) AS is_available
FROM movie_schedule ms
    INNER JOIN seat s ON s.screen_id = ms.screen_id
    LEFT JOIN seat_lock sl ON sl.seat_id = s.id
    AND sl.schedule_id = p_schedule_id
    AND (sl.locked_at + INTERVAL '5 minutes') > NOW()
    LEFT JOIN ticket_seats ts ON ts.seat_id = s.id
    LEFT JOIN ticket t ON t.id = ts.ticket_id
    AND t.schedule_id = p_schedule_id
    AND t.status <> 'PENDING'
WHERE ms.id = p_schedule_id;
$$ LANGUAGE sql STABLE;