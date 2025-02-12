package com.academy.backend.service.enrollment;

import com.academy.backend.domain.enrollment.Enrollment;
import com.academy.backend.dto.request.enrollment.EnrollmentCreateRequest;
import com.academy.backend.dto.response.enrollment.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    void createEnrollment(String header, EnrollmentCreateRequest request);
    List<EnrollmentResponse> getEnrollments(String header);
    EnrollmentResponse getEnrollmentById(Long enrollmentId);
    Enrollment getEnrollmentEntityById(Long enrollmentId);
}
