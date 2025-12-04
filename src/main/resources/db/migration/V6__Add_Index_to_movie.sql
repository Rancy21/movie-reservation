CREATE extension if not exists pg_trgm;
create index concurrently idx_movie_genre on movie(genre);
create index concurrently idx_movie_title_trgm on movie using gin (title gin_trgm_ops);
create index concurrently idx_movie_description_trgm using gin (description gin_trgm_ops);
create index concurrently idx_movie_genre_title on movie(genre) include (title);
create index concurrently idx_movie_genre_and_title_trgm on movie using gin (genre, title gin trgm_ops);