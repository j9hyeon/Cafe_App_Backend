package com.example.cafe_reservation.controller;

import com.example.cafe_reservation.domain.Seat;
import com.example.cafe_reservation.repository.SeatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatRepository seatRepo;

    // 생성자 주입 (Lombok 없이 수동으로 작성)
    public SeatController(SeatRepository seatRepo) {
        this.seatRepo = seatRepo;
    }

    // ✅ 좌석 전체 조회
    @GetMapping
    public List<Seat> getAllSeats() {
        return seatRepo.findAll();  // DB에서 모든 좌석 가져옴
    }

    // ✅ 좌석 예약
    @PostMapping("/{id}/reserve")
    public ResponseEntity<?> reserveSeat(@PathVariable Long id) {
        Seat seat = seatRepo.findById(id).orElse(null);
        if (seat == null) {
            return ResponseEntity.notFound().build();
        }
        if (seat.isReserved()) {
            return ResponseEntity.badRequest().body("이미 예약된 좌석입니다.");
        }
        seat.setReserved(true);
        seatRepo.save(seat);
        return ResponseEntity.ok("예약 성공");
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelSeat(@PathVariable Long id) {
        Seat seat = seatRepo.findById(id).orElse(null);
        if (seat == null) {
            return ResponseEntity.notFound().build();
        }
        if (!seat.isReserved()) {
            return ResponseEntity.badRequest().body("이미 비어 있는 좌석입니다.");
        }
        seat.setReserved(false);
        seatRepo.save(seat);
        return ResponseEntity.ok("예약 취소 완료");
    }
}
