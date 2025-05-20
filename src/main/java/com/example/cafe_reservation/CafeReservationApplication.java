package com.example.cafe_reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // ✅ 스케줄링 활성화
public class CafeReservationApplication {
	public static void main(String[] args) {
		SpringApplication.run(CafeReservationApplication.class, args);
	}
}
