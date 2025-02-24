package com.academy.backend.enrollment.controller;

import com.academy.backend.enrollment.dto.request.EnrollmentCreateRequest;
import com.academy.backend.enrollment.dto.response.EnrollmentResponse;
import com.academy.backend.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollments(@RequestHeader("Authorization") String authorizationHeader) {
        List<EnrollmentResponse> enrollments = enrollmentService.getEnrollments(authorizationHeader);

        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(@PathVariable Long enrollmentId) {
        EnrollmentResponse enrollment = enrollmentService.getEnrollmentById(enrollmentId);

        return ResponseEntity.ok(enrollment);
    }
}
