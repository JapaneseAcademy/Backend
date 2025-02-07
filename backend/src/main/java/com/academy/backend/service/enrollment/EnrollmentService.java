package com.academy.backend.service.enrollment;

import com.academy.backend.dto.request.enrollment.EnrollmentCreateRequest;

public interface EnrollmentService {
    void createEnrollment(String header, EnrollmentCreateRequest request);
}
