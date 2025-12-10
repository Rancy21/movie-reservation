package com.larr.movie_reservation_app.service.mapper;

import org.springframework.stereotype.Component;

import com.larr.movie_reservation_app.dto.MovieDTO;
import com.larr.movie_reservation_app.dto.user.UserRequest;
import com.larr.movie_reservation_app.dto.user.UserResponse;
import com.larr.movie_reservation_app.model.Movie;
import com.larr.movie_reservation_app.model.User;

@Component
public class DtoMapper {
    public User toUserEntity(UserRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        return user;
    }

    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        return response;
    }

    public Movie toMovieEntity(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setDuration(dto.getDuration());
        movie.setRating(dto.getRating());
        movie.setDescription(dto.getDescription());
        movie.setPosterUrl(dto.getPosterUrl());

        return movie;
    }

    public MovieDTO toMovieDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setGenre(movie.getGenre());
        dto.setDuration(movie.getDuration());
        dto.setRating(movie.getRating());
        dto.setDescription(movie.getDescription());
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setStatus(movie.getStatus());

        return dto;

    }

}