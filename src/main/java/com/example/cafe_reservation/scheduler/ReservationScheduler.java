package com.example.cafe_reservation.scheduler;

import com.example.cafe_reservation.domain.Seat;
import com.example.cafe_reservation.repository.SeatRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReservationScheduler {

    private final SeatRepository seatRepository;

    public ReservationScheduler(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Scheduled(fixedRate = 5 * 60 * 1000) // ✅ 5분마다 실행
    public void releaseExpiredSeats() {
        List<Seat> reservedSeats = seatRepository.findAllByIsReservedTrue();
        Date now = new Date();

        for (Seat seat : reservedSeats) {
            Date reservedAt = seat.getReservedAt();
            if (reservedAt != null) {
                long elapsedMillis = now.getTime() - reservedAt.getTime();
                if (elapsedMillis >= 2 * 60 * 60 * 1000) { // ✅ 2시간 (7200000ms)
                    seat.setReserved(false);
                    seat.setReservedAt(null);
                    seatRepository.save(seat);
                    System.out.println("⏳ 예약 해제됨 → " + seat.getSeatNumber());
                }
            }
        }
    }

}
