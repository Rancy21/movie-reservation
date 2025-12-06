CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE INDEX idx_movie_genre ON movie(genre);
CREATE INDEX idx_movie_title_trgm ON movie USING gin (title gin_trgm_ops);
CREATE INDEX idx_movie_description_trgm ON movie USING gin (description gin_trgm_ops);
CREATE INDEX idx_movie_genre_title ON movie(genre) INCLUDE (title);
CREATE INDEX idx_movie_genre_and_title_trgm ON movie USING gin (genre gin_trgm_ops, title gin_trgm_ops);