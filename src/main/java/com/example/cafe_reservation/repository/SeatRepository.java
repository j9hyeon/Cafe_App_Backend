package com.example.cafe_reservation.repository;

import com.example.cafe_reservation.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
