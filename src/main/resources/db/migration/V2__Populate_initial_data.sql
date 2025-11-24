-- Insert sample users
INSERT INTO users (id, name, email, password, phone_number, is_active) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'John Doe', 'john@example.com', '$2a$10$N9qo8ucoIE0kJwbf8NRbH.SmL9TzUr8Wp3wEKl.Y2IkHCm.6j65jS', '555-0001', TRUE),
('550e8400-e29b-41d4-a716-446655440002', 'Jane Smith', 'jane@example.com', '$2a$10$N9qo8ucoIE0kJwbf8NRbH.SmL9TzUr8Wp3wEKl.Y2IkHCm.6j65jS', '555-0002', TRUE),
('550e8400-e29b-41d4-a716-446655440003', 'Mike Johnson', 'mike@example.com', '$2a$10$N9qo8ucoIE0kJwbf8NRbH.SmL9TzUr8Wp3wEKl.Y2IkHCm.6j65jS', '555-0003', TRUE),
('550e8400-e29b-41d4-a716-446655440004', 'Sarah Wilson', 'sarah@example.com', '$2a$10$N9qo8ucoIE0kJwbf8NRbH.SmL9TzUr8Wp3wEKl.Y2IkHCm.6j65jS', '555-0004', TRUE),
('550e8400-e29b-41d4-a716-446655440005', 'Tom Brown', 'tom@example.com', '$2a$10$N9qo8ucoIE0kJwbf8NRbH.SmL9TzUr8Wp3wEKl.Y2IkHCm.6j65jS', '555-0005', TRUE);

-- Insert sample theaters
INSERT INTO theater (id, name, address, city, total_screens) VALUES
('550e8400-e29b-41d4-a716-446655550001', 'Cineplex Downtown', '123 Main Street', 'New York', 8),
('550e8400-e29b-41d4-a716-446655550002', 'Vue Cinema Mall', '456 Shopping Center Lane', 'Los Angeles', 10),
('550e8400-e29b-41d4-a716-446655550003', 'Regal Theaters', '789 Plaza Avenue', 'Chicago', 6);

-- Insert sample movies
INSERT INTO movie (id, title, genre, duration, rating, description, poster_url) VALUES
('550e8400-e29b-41d4-a716-446655660001', 'The Quantum Paradox', 'Sci-Fi', 148, 'PG-13', 'A mind-bending thriller about parallel universes and time manipulation.', 'https://example.com/posters/quantum-paradox.jpg'),
('550e8400-e29b-41d4-a716-446655660002', 'Love in the City', 'Romance', 124, 'PG', 'A heartwarming love story set in the bustling streets of New York City.', 'https://example.com/posters/love-in-city.jpg'),
('550e8400-e29b-41d4-a716-446655660003', 'Shadows of the Past', 'Thriller', 135, 'R', 'A gripping murder mystery that keeps you guessing until the end.', 'https://example.com/posters/shadows-past.jpg'),
('550e8400-e29b-41d4-a716-446655660004', 'Adventure Quest', 'Action', 156, 'PG-13', 'An epic adventure across mystical lands filled with treasures and danger.', 'https://example.com/posters/adventure-quest.jpg'),
('550e8400-e29b-41d4-a716-446655660005', 'Laughs & Chaos', 'Comedy', 96, 'PG', 'A hilarious comedy about a group of misfits trying to save their hometown.', 'https://example.com/posters/laughs-chaos.jpg'),
('550e8400-e29b-41d4-a716-446655660006', 'Midnight Dreams', 'Fantasy', 142, 'PG', 'A magical journey through enchanted realms and mythical creatures.', 'https://example.com/posters/midnight-dreams.jpg'),
('550e8400-e29b-41d4-a716-446655660007', 'Code Red Protocol', 'Action', 128, 'R', 'High-octane espionage thriller with heart-pounding sequences.', 'https://example.com/posters/code-red.jpg'),
('550e8400-e29b-41d4-a716-446655660008', 'Heartstrings', 'Romance', 115, 'PG', 'A touching tale of two souls finding love against all odds.', 'https://example.com/posters/heartstrings.jpg'),
('550e8400-e29b-41d4-a716-446655660009', 'The Lost City', 'Adventure', 138, 'PG-13', 'Explorers uncover ancient secrets in a hidden city beneath the jungle.', 'https://example.com/posters/lost-city.jpg'),
('550e8400-e29b-41d4-a716-446655660010', 'Cosmic Horizons', 'Sci-Fi', 165, 'PG-13', 'Humanity reaches for the stars in this epic space exploration saga.', 'https://example.com/posters/cosmic-horizons.jpg'),
('550e8400-e29b-41d4-a716-446655660011', 'The Detective', 'Thriller', 118, 'R', 'A seasoned detective races against time to solve a serial crime case.', 'https://example.com/posters/the-detective.jpg'),
('550e8400-e29b-41d4-a716-446655660012', 'Silly Business', 'Comedy', 104, 'PG', 'When a struggling startup hires the worst employees, chaos ensues.', 'https://example.com/posters/silly-business.jpg'),
('550e8400-e29b-41d4-a716-446655660013', 'Dragon Wars', 'Fantasy', 159, 'PG-13', 'Ancient dragons awaken and threaten to destroy the kingdom.', 'https://example.com/posters/dragon-wars.jpg'),
('550e8400-e29b-41d4-a716-446655660014', 'Sunset Romance', 'Romance', 108, 'G', 'A couple rediscovers their love on a vacation to paradise.', 'https://example.com/posters/sunset-romance.jpg'),
('550e8400-e29b-41d4-a716-446655660015', 'Silent Witness', 'Thriller', 121, 'R', 'A woman becomes the only witness to a crime and must survive the killers.', 'https://example.com/posters/silent-witness.jpg'),
('550e8400-e29b-41d4-a716-446655660016', 'Jungle Expedition', 'Adventure', 145, 'PG-13', 'Scientists venture into uncharted territory searching for a legendary beast.', 'https://example.com/posters/jungle-expedition.jpg'),
('550e8400-e29b-41d4-a716-446655660017', 'Robot Evolution', 'Sci-Fi', 152, 'PG-13', 'When AI gains consciousness, humanity must decide its own fate.', 'https://example.com/posters/robot-evolution.jpg'),
('550e8400-e29b-41d4-a716-446655660018', 'The Giggle Factory', 'Comedy', 99, 'PG', 'A comedy troupe competes to become the funniest act in the nation.', 'https://example.com/posters/giggle-factory.jpg'),
('550e8400-e29b-41d4-a716-446655660019', 'Enchanted Forest', 'Fantasy', 136, 'PG', 'Children discover a magical forest where anything is possible.', 'https://example.com/posters/enchanted-forest.jpg'),
('550e8400-e29b-41d4-a716-446655660020', 'Ocean Mystery', 'Thriller', 126, 'PG-13', 'Deep-sea explorers uncover a terrifying creature lurking in the abyss.', 'https://example.com/posters/ocean-mystery.jpg'),
('550e8400-e29b-41d4-a716-446655660021', 'The Last Hero', 'Action', 144, 'R', 'One soldier must stop an invading army from destroying everything he loves.', 'https://example.com/posters/last-hero.jpg'),
('550e8400-e29b-41d4-a716-446655660022', 'Love Letters', 'Romance', 111, 'PG', 'Years apart, two lovers find each other through handwritten letters.', 'https://example.com/posters/love-letters.jpg'),
('550e8400-e29b-41d4-a716-446655660023', 'The Heist', 'Thriller', 133, 'R', 'A master thief plans the greatest heist the world has never seen.', 'https://example.com/posters/the-heist.jpg'),
('550e8400-e29b-41d4-a716-446655660024', 'Festival of Fools', 'Comedy', 102, 'PG', 'A misfit family inherits a struggling festival and must save it from collapse.', 'https://example.com/posters/festival-fools.jpg'),
('550e8400-e29b-41d4-a716-446655660025', 'Starlight Legacy', 'Sci-Fi', 159, 'PG-13', 'An aging astronaut must train the next generation to save the galaxy.', 'https://example.com/posters/starlight-legacy.jpg');

-- Insert screens for Cineplex Downtown (New York)
INSERT INTO screen (id, theater_id, screen_number, total_rows, seats_per_row) VALUES
('550e8400-e29b-41d4-a716-446655770001', '550e8400-e29b-41d4-a716-446655550001', 1, 10, 15),
('550e8400-e29b-41d4-a716-446655770002', '550e8400-e29b-41d4-a716-446655550001', 2, 12, 20),
('550e8400-e29b-41d4-a716-446655770003', '550e8400-e29b-41d4-a716-446655550001', 3, 8, 12);

-- Insert screens for Vue Cinema Mall (Los Angeles)
INSERT INTO screen (id, theater_id, screen_number, total_rows, seats_per_row) VALUES
('550e8400-e29b-41d4-a716-446655770004', '550e8400-e29b-41d4-a716-446655550002', 1, 12, 18),
('550e8400-e29b-41d4-a716-446655770005', '550e8400-e29b-41d4-a716-446655550002', 2, 14, 22),
('550e8400-e29b-41d4-a716-446655770006', '550e8400-e29b-41d4-a716-446655550002', 3, 10, 16);

-- Insert screens for Regal Theaters (Chicago)
INSERT INTO screen (id, theater_id, screen_number, total_rows, seats_per_row) VALUES
('550e8400-e29b-41d4-a716-446655770007', '550e8400-e29b-41d4-a716-446655550003', 1, 10, 14),
('550e8400-e29b-41d4-a716-446655770008', '550e8400-e29b-41d4-a716-446655550003', 2, 11, 16);

-- Insert seats for Screen 1 of Cineplex Downtown (10 rows x 15 seats)
INSERT INTO seat (id, screen_id, seat_number, row, number, type) VALUES
('550e8400-e29b-41d4-a716-446655880001', '550e8400-e29b-41d4-a716-446655770001', 'A1', 'A', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655880002', '550e8400-e29b-41d4-a716-446655770001', 'A2', 'A', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655880003', '550e8400-e29b-41d4-a716-446655770001', 'A3', 'A', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655880004', '550e8400-e29b-41d4-a716-446655770001', 'A4', 'A', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655880005', '550e8400-e29b-41d4-a716-446655770001', 'A5', 'A', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655880006', '550e8400-e29b-41d4-a716-446655770001', 'A6', 'A', 6, 'Regular'),
('550e8400-e29b-41d4-a716-446655880007', '550e8400-e29b-41d4-a716-446655770001', 'A7', 'A', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655880008', '550e8400-e29b-41d4-a716-446655770001', 'A8', 'A', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655880009', '550e8400-e29b-41d4-a716-446655770001', 'A9', 'A', 9, 'Premium'),
('550e8400-e29b-41d4-a716-446655880010', '550e8400-e29b-41d4-a716-446655770001', 'A10', 'A', 10, 'Premium'),
('550e8400-e29b-41d4-a716-446655880011', '550e8400-e29b-41d4-a716-446655770001', 'A11', 'A', 11, 'Regular'),
('550e8400-e29b-41d4-a716-446655880012', '550e8400-e29b-41d4-a716-446655770001', 'A12', 'A', 12, 'Regular'),
('550e8400-e29b-41d4-a716-446655880013', '550e8400-e29b-41d4-a716-446655770001', 'A13', 'A', 13, 'Regular'),
('550e8400-e29b-41d4-a716-446655880014', '550e8400-e29b-41d4-a716-446655770001', 'A14', 'A', 14, 'Regular'),
('550e8400-e29b-41d4-a716-446655880015', '550e8400-e29b-41d4-a716-446655770001', 'A15', 'A', 15, 'Recliner'),
('550e8400-e29b-41d4-a716-446655880016', '550e8400-e29b-41d4-a716-446655770001', 'B1', 'B', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655880017', '550e8400-e29b-41d4-a716-446655770001', 'B2', 'B', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655770001', '550e8400-e29b-41d4-a716-446655770001', 'B3', 'B', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655880019', '550e8400-e29b-41d4-a716-446655770001', 'B4', 'B', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655880020', '550e8400-e29b-41d4-a716-446655770001', 'B5', 'B', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655880021', '550e8400-e29b-41d4-a716-446655770001', 'C1', 'C', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655880022', '550e8400-e29b-41d4-a716-446655770001', 'C2', 'C', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655880023', '550e8400-e29b-41d4-a716-446655770001', 'C3', 'C', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655880024', '550e8400-e29b-41d4-a716-446655770001', 'C4', 'C', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655880025', '550e8400-e29b-41d4-a716-446655770001', 'C5', 'C', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655880026', '550e8400-e29b-41d4-a716-446655770001', 'C6', 'C', 6, 'Regular'),
('550e8400-e29b-41d4-a716-446655880027', '550e8400-e29b-41d4-a716-446655770001', 'C7', 'C', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655880028', '550e8400-e29b-41d4-a716-446655770001', 'C8', 'C', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655880029', '550e8400-e29b-41d4-a716-446655770001', 'C9', 'C', 9, 'Premium'),
('550e8400-e29b-41d4-a716-446655880030', '550e8400-e29b-41d4-a716-446655770001', 'C10', 'C', 10, 'Premium'),
('550e8400-e29b-41d4-a716-446655880031', '550e8400-e29b-41d4-a716-446655770001', 'C11', 'C', 11, 'Regular'),
('550e8400-e29b-41d4-a716-446655880032', '550e8400-e29b-41d4-a716-446655770001', 'C12', 'C', 12, 'Regular'),
('550e8400-e29b-41d4-a716-446655880033', '550e8400-e29b-41d4-a716-446655770001', 'C13', 'C', 13, 'Regular'),
('550e8400-e29b-41d4-a716-446655880034', '550e8400-e29b-41d4-a716-446655770001', 'C14', 'C', 14, 'Regular'),
('550e8400-e29b-41d4-a716-446655880035', '550e8400-e29b-41d4-a716-446655770001', 'C15', 'C', 15, 'Recliner'),
('550e8400-e29b-41d4-a716-446655880036', '550e8400-e29b-41d4-a716-446655770001', 'D1', 'D', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655880037', '550e8400-e29b-41d4-a716-446655770001', 'D2', 'D', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655880038', '550e8400-e29b-41d4-a716-446655770001', 'D3', 'D', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655880039', '550e8400-e29b-41d4-a716-446655770001', 'D4', 'D', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655880040', '550e8400-e29b-41d4-a716-446655770001', 'D5', 'D', 5, 'Regular');

-- Insert seats for Screen 2 of Cineplex Downtown (12 rows x 20 seats)
INSERT INTO seat (id, screen_id, seat_number, row, number, type) VALUES
('550e8400-e29b-41d4-a716-446655881001', '550e8400-e29b-41d4-a716-446655770002', 'A1', 'A', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655881002', '550e8400-e29b-41d4-a716-446655770002', 'A2', 'A', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655881003', '550e8400-e29b-41d4-a716-446655770002', 'A3', 'A', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655881004', '550e8400-e29b-41d4-a716-446655770002', 'A4', 'A', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655881005', '550e8400-e29b-41d4-a716-446655770002', 'A5', 'A', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655881006', '550e8400-e29b-41d4-a716-446655770002', 'A6', 'A', 6, 'Regular'),
('550e8400-e29b-41d4-a716-446655881007', '550e8400-e29b-41d4-a716-446655770002', 'A7', 'A', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655881008', '550e8400-e29b-41d4-a716-446655770002', 'A8', 'A', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655881009', '550e8400-e29b-41d4-a716-446655770002', 'A9', 'A', 9, 'Premium'),
('550e8400-e29b-41d4-a716-446655881010', '550e8400-e29b-41d4-a716-446655770002', 'A10', 'A', 10, 'Premium'),
('550e8400-e29b-41d4-a716-446655881011', '550e8400-e29b-41d4-a716-446655770002', 'A11', 'A', 11, 'Premium'),
('550e8400-e29b-41d4-a716-446655881012', '550e8400-e29b-41d4-a716-446655770002', 'A12', 'A', 12, 'Premium'),
('550e8400-e29b-41d4-a716-446655881013', '550e8400-e29b-41d4-a716-446655770002', 'A13', 'A', 13, 'Premium'),
('550e8400-e29b-41d4-a716-446655881014', '550e8400-e29b-41d4-a716-446655770002', 'A14', 'A', 14, 'Regular'),
('550e8400-e29b-41d4-a716-446655881015', '550e8400-e29b-41d4-a716-446655770002', 'A15', 'A', 15, 'Regular'),
('550e8400-e29b-41d4-a716-446655881016', '550e8400-e29b-41d4-a716-446655770002', 'A16', 'A', 16, 'Regular'),
('550e8400-e29b-41d4-a716-446655881017', '550e8400-e29b-41d4-a716-446655770002', 'A17', 'A', 17, 'Regular'),
('550e8400-e29b-41d4-a716-446655881018', '550e8400-e29b-41d4-a716-446655770002', 'A18', 'A', 18, 'Regular'),
('550e8400-e29b-41d4-a716-446655881019', '550e8400-e29b-41d4-a716-446655770002', 'A19', 'A', 19, 'Recliner'),
('550e8400-e29b-41d4-a716-446655881020', '550e8400-e29b-41d4-a716-446655770002', 'A20', 'A', 20, 'Recliner'),
('550e8400-e29b-41d4-a716-446655881021', '550e8400-e29b-41d4-a716-446655770002', 'B1', 'B', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655881022', '550e8400-e29b-41d4-a716-446655770002', 'B2', 'B', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655881023', '550e8400-e29b-41d4-a716-446655770002', 'B3', 'B', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655881024', '550e8400-e29b-41d4-a716-446655770002', 'B4', 'B', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655881025', '550e8400-e29b-41d4-a716-446655770002', 'B5', 'B', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655881026', '550e8400-e29b-41d4-a716-446655770002', 'B6', 'B', 6, 'Regular'),
('550e8400-e29b-41d4-a716-446655881027', '550e8400-e29b-41d4-a716-446655770002', 'B7', 'B', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655881028', '550e8400-e29b-41d4-a716-446655770002', 'B8', 'B', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655881029', '550e8400-e29b-41d4-a716-446655770002', 'B9', 'B', 9, 'Premium'),
('550e8400-e29b-41d4-a716-446655881030', '550e8400-e29b-41d4-a716-446655770002', 'B10', 'B', 10, 'Premium'),
('550e8400-e29b-41d4-a716-446655881031', '550e8400-e29b-41d4-a716-446655770002', 'B11', 'B', 11, 'Premium'),
('550e8400-e29b-41d4-a716-446655881032', '550e8400-e29b-41d4-a716-446655770002', 'B12', 'B', 12, 'Premium'),
('550e8400-e29b-41d4-a716-446655881033', '550e8400-e29b-41d4-a716-446655770002', 'B13', 'B', 13, 'Premium'),
('550e8400-e29b-41d4-a716-446655881034', '550e8400-e29b-41d4-a716-446655770002', 'B14', 'B', 14, 'Regular'),
('550e8400-e29b-41d4-a716-446655881035', '550e8400-e29b-41d4-a716-446655770002', 'B15', 'B', 15, 'Regular'),
('550e8400-e29b-41d4-a716-446655881036', '550e8400-e29b-41d4-a716-446655770002', 'B16', 'B', 16, 'Regular'),
('550e8400-e29b-41d4-a716-446655881037', '550e8400-e29b-41d4-a716-446655770002', 'B17', 'B', 17, 'Regular'),
('550e8400-e29b-41d4-a716-446655881038', '550e8400-e29b-41d4-a716-446655770002', 'B18', 'B', 18, 'Regular'),
('550e8400-e29b-41d4-a716-446655881039', '550e8400-e29b-41d4-a716-446655770002', 'B19', 'B', 19, 'Recliner'),
('550e8400-e29b-41d4-a716-446655881040', '550e8400-e29b-41d4-a716-446655770002', 'B20', 'B', 20, 'Recliner');

-- Insert seats for Screen 3 of Cineplex Downtown (8 rows x 12 seats)
INSERT INTO seat (id, screen_id, seat_number, row, number, type) VALUES
('550e8400-e29b-41d4-a716-446655882001', '550e8400-e29b-41d4-a716-446655770003', 'A1', 'A', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655882002', '550e8400-e29b-41d4-a716-446655770003', 'A2', 'A', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655882003', '550e8400-e29b-41d4-a716-446655770003', 'A3', 'A', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655882004', '550e8400-e29b-41d4-a716-446655770003', 'A4', 'A', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655882005', '550e8400-e29b-41d4-a716-446655770003', 'A5', 'A', 5, 'Premium'),
('550e8400-e29b-41d4-a716-446655882006', '550e8400-e29b-41d4-a716-446655770003', 'A6', 'A', 6, 'Premium'),
('550e8400-e29b-41d4-a716-446655882007', '550e8400-e29b-41d4-a716-446655770003', 'A7', 'A', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655882008', '550e8400-e29b-41d4-a716-446655770003', 'A8', 'A', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655882009', '550e8400-e29b-41d4-a716-446655770003', 'A9', 'A', 9, 'Regular'),
('550e8400-e29b-41d4-a716-446655882010', '550e8400-e29b-41d4-a716-446655770003', 'A10', 'A', 10, 'Regular'),
('550e8400-e29b-41d4-a716-446655882011', '550e8400-e29b-41d4-a716-446655770003', 'A11', 'A', 11, 'Recliner'),
('550e8400-e29b-41d4-a716-446655882012', '550e8400-e29b-41d4-a716-446655770003', 'A12', 'A', 12, 'Recliner');

-- Insert seats for Screen 1 of Vue Cinema Mall (12 rows x 18 seats)
INSERT INTO seat (id, screen_id, seat_number, row, number, type) VALUES
('550e8400-e29b-41d4-a716-446655883001', '550e8400-e29b-41d4-a716-446655770004', 'A1', 'A', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655883002', '550e8400-e29b-41d4-a716-446655770004', 'A2', 'A', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655883003', '550e8400-e29b-41d4-a716-446655770004', 'A3', 'A', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655883004', '550e8400-e29b-41d4-a716-446655770004', 'A4', 'A', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655883005', '550e8400-e29b-41d4-a716-446655770004', 'A5', 'A', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655883006', '550e8400-e29b-41d4-a716-446655770004', 'A6', 'A', 6, 'Regular'),
('550e8400-e29b-41d4-a716-446655883007', '550e8400-e29b-41d4-a716-446655770004', 'A7', 'A', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655883008', '550e8400-e29b-41d4-a716-446655770004', 'A8', 'A', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655883009', '550e8400-e29b-41d4-a716-446655770004', 'A9', 'A', 9, 'Premium'),
('550e8400-e29b-41d4-a716-446655883010', '550e8400-e29b-41d4-a716-446655770004', 'A10', 'A', 10, 'Premium'),
('550e8400-e29b-41d4-a716-446655883011', '550e8400-e29b-41d4-a716-446655770004', 'A11', 'A', 11, 'Premium'),
('550e8400-e29b-41d4-a716-446655883012', '550e8400-e29b-41d4-a716-446655770004', 'A12', 'A', 12, 'Regular'),
('550e8400-e29b-41d4-a716-446655883013', '550e8400-e29b-41d4-a716-446655770004', 'A13', 'A', 13, 'Regular'),
('550e8400-e29b-41d4-a716-446655883014', '550e8400-e29b-41d4-a716-446655770004', 'A14', 'A', 14, 'Regular'),
('550e8400-e29b-41d4-a716-446655883015', '550e8400-e29b-41d4-a716-446655770004', 'A15', 'A', 15, 'Regular'),
('550e8400-e29b-41d4-a716-446655883016', '550e8400-e29b-41d4-a716-446655770004', 'A16', 'A', 16, 'Recliner'),
('550e8400-e29b-41d4-a716-446655883017', '550e8400-e29b-41d4-a716-446655770004', 'A17', 'A', 17, 'Recliner'),
('550e8400-e29b-41d4-a716-446655883018', '550e8400-e29b-41d4-a716-446655770004', 'A18', 'A', 18, 'Recliner'),
('550e8400-e29b-41d4-a716-446655883019', '550e8400-e29b-41d4-a716-446655770004', 'B1', 'B', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655883020', '550e8400-e29b-41d4-a716-446655770004', 'B2', 'B', 2, 'Regular');

-- Insert seats for Screen 2 of Vue Cinema Mall (14 rows x 22 seats)
INSERT INTO seat (id, screen_id, seat_number, row, number, type) VALUES
('550e8400-e29b-41d4-a716-446655884001', '550e8400-e29b-41d4-a716-446655770005', 'A1', 'A', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655884002', '550e8400-e29b-41d4-a716-446655770005', 'A2', 'A', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655884003', '550e8400-e29b-41d4-a716-446655770005', 'A3', 'A', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655884004', '550e8400-e29b-41d4-a716-446655770005', 'A4', 'A', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655884005', '550e8400-e29b-41d4-a716-446655770005', 'A5', 'A', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655884006', '550e8400-e29b-41d4-a716-446655770005', 'A6', 'A', 6, 'Regular'),
('550e8400-e29b-41d4-a716-446655884007', '550e8400-e29b-41d4-a716-446655770005', 'A7', 'A', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655884008', '550e8400-e29b-41d4-a716-446655770005', 'A8', 'A', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655884009', '550e8400-e29b-41d4-a716-446655770005', 'A9', 'A', 9, 'Premium'),
('550e8400-e29b-41d4-a716-446655884010', '550e8400-e29b-41d4-a716-446655770005', 'A10', 'A', 10, 'Premium'),
('550e8400-e29b-41d4-a716-446655884011', '550e8400-e29b-41d4-a716-446655770005', 'A11', 'A', 11, 'Premium'),
('550e8400-e29b-41d4-a716-446655884012', '550e8400-e29b-41d4-a716-446655770005', 'A12', 'A', 12, 'Premium'),
('550e8400-e29b-41d4-a716-446655884013', '550e8400-e29b-41d4-a716-446655770005', 'A13', 'A', 13, 'Premium'),
('550e8400-e29b-41d4-a716-446655884014', '550e8400-e29b-41d4-a716-446655770005', 'A14', 'A', 14, 'Premium'),
('550e8400-e29b-41d4-a716-446655884015', '550e8400-e29b-41d4-a716-446655770005', 'A15', 'A', 15, 'Premium'),
('550e8400-e29b-41d4-a716-446655884016', '550e8400-e29b-41d4-a716-446655770005', 'A16', 'A', 16, 'Regular'),
('550e8400-e29b-41d4-a716-446655884017', '550e8400-e29b-41d4-a716-446655770005', 'A17', 'A', 17, 'Regular'),
('550e8400-e29b-41d4-a716-446655884018', '550e8400-e29b-41d4-a716-446655770005', 'A18', 'A', 18, 'Regular'),
('550e8400-e29b-41d4-a716-446655884019', '550e8400-e29b-41d4-a716-446655770005', 'A19', 'A', 19, 'Recliner'),
('550e8400-e29b-41d4-a716-446655884020', '550e8400-e29b-41d4-a716-446655770005', 'A20', 'A', 20, 'Recliner'),
('550e8400-e29b-41d4-a716-446655884021', '550e8400-e29b-41d4-a716-446655770005', 'A21', 'A', 21, 'Recliner'),
('550e8400-e29b-41d4-a716-446655884022', '550e8400-e29b-41d4-a716-446655770005', 'A22', 'A', 22, 'Recliner');

-- Insert seats for Screen 3 of Vue Cinema Mall (10 rows x 16 seats)
INSERT INTO seat (id, screen_id, seat_number, row, number, type) VALUES
('550e8400-e29b-41d4-a716-446655885001', '550e8400-e29b-41d4-a716-446655770006', 'A1', 'A', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655885002', '550e8400-e29b-41d4-a716-446655770006', 'A2', 'A', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655885003', '550e8400-e29b-41d4-a716-446655770006', 'A3', 'A', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655885004', '550e8400-e29b-41d4-a716-446655770006', 'A4', 'A', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655885005', '550e8400-e29b-41d4-a716-446655770006', 'A5', 'A', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655885006', '550e8400-e29b-41d4-a716-446655770006', 'A6', 'A', 6, 'Premium'),
('550e8400-e29b-41d4-a716-446655885007', '550e8400-e29b-41d4-a716-446655770006', 'A7', 'A', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655885008', '550e8400-e29b-41d4-a716-446655770006', 'A8', 'A', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655885009', '550e8400-e29b-41d4-a716-446655770006', 'A9', 'A', 9, 'Premium'),
('550e8400-e29b-41d4-a716-446655885010', '550e8400-e29b-41d4-a716-446655770006', 'A10', 'A', 10, 'Regular'),
('550e8400-e29b-41d4-a716-446655885011', '550e8400-e29b-41d4-a716-446655770006', 'A11', 'A', 11, 'Regular'),
('550e8400-e29b-41d4-a716-446655885012', '550e8400-e29b-41d4-a716-446655770006', 'A12', 'A', 12, 'Regular'),
('550e8400-e29b-41d4-a716-446655885013', '550e8400-e29b-41d4-a716-446655770006', 'A13', 'A', 13, 'Regular'),
('550e8400-e29b-41d4-a716-446655885014', '550e8400-e29b-41d4-a716-446655770006', 'A14', 'A', 14, 'Recliner'),
('550e8400-e29b-41d4-a716-446655885015', '550e8400-e29b-41d4-a716-446655770006', 'A15', 'A', 15, 'Recliner'),
('550e8400-e29b-41d4-a716-446655885016', '550e8400-e29b-41d4-a716-446655770006', 'A16', 'A', 16, 'Recliner');

-- Insert seats for Screen 1 of Regal Theaters (10 rows x 14 seats)
INSERT INTO seat (id, screen_id, seat_number, row, number, type) VALUES
('550e8400-e29b-41d4-a716-446655886001', '550e8400-e29b-41d4-a716-446655770007', 'A1', 'A', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655886002', '550e8400-e29b-41d4-a716-446655770007', 'A2', 'A', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655886003', '550e8400-e29b-41d4-a716-446655770007', 'A3', 'A', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655886004', '550e8400-e29b-41d4-a716-446655770007', 'A4', 'A', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655886005', '550e8400-e29b-41d4-a716-446655770007', 'A5', 'A', 5, 'Premium'),
('550e8400-e29b-41d4-a716-446655886006', '550e8400-e29b-41d4-a716-446655770007', 'A6', 'A', 6, 'Premium'),
('550e8400-e29b-41d4-a716-446655886007', '550e8400-e29b-41d4-a716-446655770007', 'A7', 'A', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655886008', '550e8400-e29b-41d4-a716-446655770007', 'A8', 'A', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655886009', '550e8400-e29b-41d4-a716-446655770007', 'A9', 'A', 9, 'Regular'),
('550e8400-e29b-41d4-a716-446655886010', '550e8400-e29b-41d4-a716-446655770007', 'A10', 'A', 10, 'Regular'),
('550e8400-e29b-41d4-a716-446655886011', '550e8400-e29b-41d4-a716-446655770007', 'A11', 'A', 11, 'Regular'),
('550e8400-e29b-41d4-a716-446655886012', '550e8400-e29b-41d4-a716-446655770007', 'A12', 'A', 12, 'Regular'),
('550e8400-e29b-41d4-a716-446655886013', '550e8400-e29b-41d4-a716-446655770007', 'A13', 'A', 13, 'Recliner'),
('550e8400-e29b-41d4-a716-446655886014', '550e8400-e29b-41d4-a716-446655770007', 'A14', 'A', 14, 'Recliner');

-- Insert seats for Screen 2 of Regal Theaters (11 rows x 16 seats)
INSERT INTO seat (id, screen_id, seat_number, row, number, type) VALUES
('550e8400-e29b-41d4-a716-446655887001', '550e8400-e29b-41d4-a716-446655770008', 'A1', 'A', 1, 'Regular'),
('550e8400-e29b-41d4-a716-446655887002', '550e8400-e29b-41d4-a716-446655770008', 'A2', 'A', 2, 'Regular'),
('550e8400-e29b-41d4-a716-446655887003', '550e8400-e29b-41d4-a716-446655770008', 'A3', 'A', 3, 'Regular'),
('550e8400-e29b-41d4-a716-446655887004', '550e8400-e29b-41d4-a716-446655770008', 'A4', 'A', 4, 'Regular'),
('550e8400-e29b-41d4-a716-446655887005', '550e8400-e29b-41d4-a716-446655770008', 'A5', 'A', 5, 'Regular'),
('550e8400-e29b-41d4-a716-446655887006', '550e8400-e29b-41d4-a716-446655770008', 'A6', 'A', 6, 'Regular'),
('550e8400-e29b-41d4-a716-446655887007', '550e8400-e29b-41d4-a716-446655770008', 'A7', 'A', 7, 'Premium'),
('550e8400-e29b-41d4-a716-446655887008', '550e8400-e29b-41d4-a716-446655770008', 'A8', 'A', 8, 'Premium'),
('550e8400-e29b-41d4-a716-446655887009', '550e8400-e29b-41d4-a716-446655770008', 'A9', 'A', 9, 'Premium'),
('550e8400-e29b-41d4-a716-446655887010', '550e8400-e29b-41d4-a716-446655770008', 'A10', 'A', 10, 'Premium'),
('550e8400-e29b-41d4-a716-446655887011', '550e8400-e29b-41d4-a716-446655770008', 'A11', 'A', 11, 'Regular'),
('550e8400-e29b-41d4-a716-446655887012', '550e8400-e29b-41d4-a716-446655770008', 'A12', 'A', 12, 'Regular'),
('550e8400-e29b-41d4-a716-446655887013', '550e8400-e29b-41d4-a716-446655770008', 'A13', 'A', 13, 'Regular'),
('550e8400-e29b-41d4-a716-446655887014', '550e8400-e29b-41d4-a716-446655770008', 'A14', 'A', 14, 'Regular'),
('550e8400-e29b-41d4-a716-446655887015', '550e8400-e29b-41d4-a716-446655770008', 'A15', 'A', 15, 'Recliner'),
('550e8400-e29b-41d4-a716-446655887016', '550e8400-e29b-41d4-a716-446655770008', 'A16', 'A', 16, 'Recliner');

-- Insert sample movie schedules
INSERT INTO movie_schedule (id, movie_id, screen_id, start_time, end_time, ticket_price) VALUES
('550e8400-e29b-41d4-a716-446655990001', '550e8400-e29b-41d4-a716-446655660001', '550e8400-e29b-41d4-a716-446655770001', '2025-12-15 14:00:00', '2025-11-24 16:28:00', 12.50),
('550e8400-e29b-41d4-a716-446655990002', '550e8400-e29b-41d4-a716-446655660001', '550e8400-e29b-41d4-a716-446655770001', '2025-12-15 17:00:00', '2025-11-24 19:28:00', 12.50),
('550e8400-e29b-41d4-a716-446655990003', '550e8400-e29b-41d4-a716-446655660001', '550e8400-e29b-41d4-a716-446655770001', '2025-12-15 20:00:00', '2025-11-24 22:28:00', 14.00),
('550e8400-e29b-41d4-a716-446655990004', '550e8400-e29b-41d4-a716-446655660002', '550e8400-e29b-41d4-a716-446655770002', '2025-12-15 13:00:00', '2025-11-24 15:04:00', 11.50),
('550e8400-e29b-41d4-a716-446655990005', '550e8400-e29b-41d4-a716-446655660002', '550e8400-e29b-41d4-a716-446655770002', '2025-11-28 16:00:00', '2025-11-24 18:04:00', 11.50),
('550e8400-e29b-41d4-a716-446655990006', '550e8400-e29b-41d4-a716-446655660003', '550e8400-e29b-41d4-a716-446655770002', '2025-11-28 19:00:00', '2025-11-24 21:15:00', 13.00),
('550e8400-e29b-41d4-a716-446655990007', '550e8400-e29b-41d4-a716-446655660004', '550e8400-e29b-41d4-a716-446655770003', '2025-11-28 12:00:00', '2025-11-24 14:36:00', 12.00),
('550e8400-e29b-41d4-a716-446655990008', '550e8400-e29b-41d4-a716-446655660005', '550e8400-e29b-41d4-a716-446655770003', '2025-11-28 18:30:00', '2025-11-24 20:06:00', 10.50),
('550e8400-e29b-41d4-a716-446655990009', '550e8400-e29b-41d4-a716-446655660001', '550e8400-e29b-41d4-a716-446655770004', '2025-11-28 14:00:00', '2025-11-25 16:28:00', 13.00),
('550e8400-e29b-41d4-a716-446655990010', '550e8400-e29b-41d4-a716-446655660002', '550e8400-e29b-41d4-a716-446655770005', '2025-11-28 13:00:00', '2025-11-25 15:04:00', 12.50);

-- Note: Tickets and ticket_seats are initially empty
-- They will be populated when users make reservations through the application
