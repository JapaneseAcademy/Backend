package com.academy.backend.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/cors")
    public ResponseEntity<?> getCorsTest() {
        CorsResponseDto responseDto = new CorsResponseDto("CORS GET Success", LocalDateTime.now());
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/cors")
    public ResponseEntity<?> postCorsTest(@RequestParam String message) {
        CorsResponseDto responseDto = new CorsResponseDto(message + " CORS POST Success", LocalDateTime.now());
        return ResponseEntity.ok(responseDto);
    }

    public record CorsResponseDto(String message, LocalDateTime time) {
    }
}
