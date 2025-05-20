package com.example.cafe_reservation.config;

import com.example.cafe_reservation.domain.Seat;
import com.example.cafe_reservation.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeatDataInitializer {

    @Bean
    public CommandLineRunner initSeats(SeatRepository seatRepository) {
        return args -> {
            seatRepository.deleteAll(); // 👈 전체 삭제
            seatRepository.save(new Seat(null, "A1", false));
            seatRepository.save(new Seat(null, "A2", false));
            seatRepository.save(new Seat(null, "B1", false));
            seatRepository.save(new Seat(null, "B2", false));
            seatRepository.save(new Seat(null, "C1", false));
            seatRepository.save(new Seat(null, "C2", false));
            System.out.println("✅ 좌석 초기화 완료");
        };
    }
}
