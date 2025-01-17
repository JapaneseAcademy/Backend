package com.academy.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/cors")
    public ResponseEntity<?> getCorsTest() {
        CorsResponseDto responseDto = new CorsResponseDto("CORS GET Success", LocalDateTime.now());
        return ResponseEntity.ok(responseDto);
    }

    public record CorsResponseDto(String message, LocalDateTime time) {
    }
}
