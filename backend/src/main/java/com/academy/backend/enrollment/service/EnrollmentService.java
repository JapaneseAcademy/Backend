package com.academy.backend.enrollment.service;

import com.academy.backend.enrollment.domain.Enrollment;
import com.academy.backend.enrollment.dto.request.EnrollmentCreateRequest;
import com.academy.backend.enrollment.dto.response.EnrollmentDetailResponse;
import com.academy.backend.enrollment.dto.response.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    void createEnrollment(EnrollmentCreateRequest request);

    List<EnrollmentResponse> getEnrollmentsForUser();

    EnrollmentDetailResponse getEnrollmentById(Long enrollmentId);
}
