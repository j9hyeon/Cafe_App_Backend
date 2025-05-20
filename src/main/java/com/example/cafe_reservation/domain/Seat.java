package com.example.cafe_reservation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    private boolean isReserved = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reservedAt;

    public Seat() {}

    public Seat(Long id, String seatNumber, boolean isReserved) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.isReserved = isReserved;
    }

    public Long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public Date getReservedAt() {
        return reservedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public void setReservedAt(Date reservedAt) {
        this.reservedAt = reservedAt;
    }
}
