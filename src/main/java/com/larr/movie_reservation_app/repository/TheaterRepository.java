package com.larr.movie_reservation_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.larr.movie_reservation_app.dto.theater.TheaterDTO;
import com.larr.movie_reservation_app.dto.theater.TheaterDetailDTO;
import com.larr.movie_reservation_app.model.Theater;
import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, String> {
        @Query("select new com.larr.movie_reservation_app.dto.theater.TheaterDetailDTO(t.id, t.name, t.address, t.city, t.totalScreens) "
                        +
                        "from Theater t where t.id=:id")

        Optional<TheaterDetailDTO> findByIdAsDto(@Param("id") String id);

        @Query("select new com.larr.movie_reservation_app.dto.theater.TheaterDTO(t.id, t.name, t.city)"
                        +
                        "from Theater t where t.isActive = true")
        Page<TheaterDTO> listAllActive(Pageable pageable);

        @Query("select new com.larr.movie_reservation_app.dto.theater.TheaterDTO(t.id, t.name, t.city) from Theater t")
        Page<TheaterDTO> listAll(Pageable pageable);

        // Optional<Theater> findByNameWhereIsActiveTrue(String name);
}