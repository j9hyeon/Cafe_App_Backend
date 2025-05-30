package com.example.cafe_reservation.controller;

import com.example.cafe_reservation.dto.LoginRequest;
import com.example.cafe_reservation.dto.LoginResponse;
import com.example.cafe_reservation.dto.SignupRequest;
import com.example.cafe_reservation.domain.User;
import com.example.cafe_reservation.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if (user.isEmpty() || !user.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

        return ResponseEntity.ok(new LoginResponse("fake-token-for-now"));
    }

    // ✅ 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 등록된 전화번호입니다.");
        }

        User newUser = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // 실제 환경에서는 암호화 필요
                .build();

        userRepository.save(newUser);
        return ResponseEntity.ok("회원가입 완료");
    }
}
