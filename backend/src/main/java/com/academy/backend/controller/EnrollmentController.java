package com.academy.backend.controller;

import com.academy.backend.dto.request.enrollment.EnrollmentCreateRequest;
import com.academy.backend.service.enrollment.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("")
    public ResponseEntity<?> createEnrollment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody EnrollmentCreateRequest request) {
        enrollmentService.createEnrollment(authorizationHeader, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
