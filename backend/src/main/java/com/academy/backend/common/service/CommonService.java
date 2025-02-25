package com.academy.backend.common.service;

import com.academy.backend.enrollment.domain.Enrollment;
import com.academy.backend.enrollment.repository.EnrollmentRepository;
import com.academy.backend.exception.enrollment.EnrollmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final EnrollmentRepository enrollmentRepository;

    @Transactional(readOnly = true)
    public Enrollment getEnrollmentByEnrollmentId(Long enrollmentId) {
         return enrollmentRepository.findById(enrollmentId)
                 .orElseThrow(() -> new EnrollmentNotFoundException(enrollmentId));
    }
}
