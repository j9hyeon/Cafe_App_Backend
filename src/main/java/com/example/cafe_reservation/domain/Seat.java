package com.example.cafe_reservation.domain;

import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    private boolean isReserved = false;

    // 기본 생성자 (JPA용)
    public Seat() {}

    // 전체 필드 생성자
    public Seat(Long id, String seatNumber, boolean isReserved) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.isReserved = isReserved;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
