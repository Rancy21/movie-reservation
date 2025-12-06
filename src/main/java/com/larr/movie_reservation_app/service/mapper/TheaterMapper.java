package com.larr.movie_reservation_app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.larr.movie_reservation_app.dto.TheaterCreateRequest;
import com.larr.movie_reservation_app.dto.TheaterDTO;
import com.larr.movie_reservation_app.model.Theater;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TheaterMapper {
    TheaterDTO toDto(Theater theater);

    Theater toEntity(TheaterCreateRequest request);
}
