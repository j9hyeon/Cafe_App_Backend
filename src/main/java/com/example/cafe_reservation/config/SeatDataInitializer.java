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
            if (seatRepository.count() == 0) {
                seatRepository.save(new Seat(null, "A1", false));
                seatRepository.save(new Seat(null, "A2", false));
                seatRepository.save(new Seat(null, "B1", false));
                seatRepository.save(new Seat(null, "B2", false));
                System.out.println("✅ 좌석 초기 데이터 삽입 완료");
            }
        };
    }
}
