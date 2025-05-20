package com.example.cafe_reservation.controller;

import com.example.cafe_reservation.domain.Seat;
import com.example.cafe_reservation.repository.SeatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatRepository seatRepo;

    public SeatController(SeatRepository seatRepo) {
        this.seatRepo = seatRepo;
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatRepo.findAll();
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<?> reserveSeat(@PathVariable Long id) {
        Seat seat = seatRepo.findById(id).orElse(null);
        if (seat == null) return ResponseEntity.notFound().build();
        if (seat.isReserved()) return ResponseEntity.badRequest().body("이미 예약된 좌석입니다.");

        seat.setReserved(true);
        seat.setReservedAt(new Date()); // ✅ 예약 시간 저장
        seatRepo.save(seat);
        return ResponseEntity.ok("예약 성공");
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelSeat(@PathVariable Long id) {
        Seat seat = seatRepo.findById(id).orElse(null);
        if (seat == null) return ResponseEntity.notFound().build();
        if (!seat.isReserved()) return ResponseEntity.badRequest().body("이미 비어 있는 좌석입니다.");

        seat.setReserved(false);
        seat.setReservedAt(null);
        seatRepo.save(seat);
        return ResponseEntity.ok("예약 취소 완료");
    }
}
