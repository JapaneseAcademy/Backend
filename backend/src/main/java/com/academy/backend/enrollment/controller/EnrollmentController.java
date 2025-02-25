package com.academy.backend.enrollment.controller;

import com.academy.backend.enrollment.dto.request.EnrollmentCreateRequest;
import com.academy.backend.enrollment.dto.response.EnrollmentDetailResponse;
import com.academy.backend.enrollment.dto.response.EnrollmentResponse;
import com.academy.backend.enrollment.service.EnrollmentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("")
    public ResponseEntity<?> createEnrollment(
            @RequestBody EnrollmentCreateRequest request
    ) {
        enrollmentService.createEnrollment(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollments() {
        List<EnrollmentResponse> response = enrollmentService.getEnrollmentsForUser();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentDetailResponse> getEnrollmentById(
            @PathVariable Long enrollmentId
    ) {
        EnrollmentDetailResponse response = enrollmentService.getEnrollmentById(enrollmentId);

        return ResponseEntity.ok(response);
    }
}
