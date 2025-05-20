package com.example.cafe_reservation.repository;

import com.example.cafe_reservation.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByIsReservedTrue();
}
