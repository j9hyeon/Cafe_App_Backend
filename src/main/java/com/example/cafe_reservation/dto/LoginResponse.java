package com.example.cafe_reservation.dto;

// LoginResponse.java
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token; // 단순 성공 여부로 "ok" 같은 값도 가능
}