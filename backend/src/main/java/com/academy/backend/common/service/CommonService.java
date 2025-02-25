package com.academy.backend.common.service;

import com.academy.backend.enrollment.domain.Enrollment;
import com.academy.backend.enrollment.repository.EnrollmentRepository;
import com.academy.backend.exception.enrollment.EnrollmentNotFoundException;
import com.academy.backend.exception.review.ReviewNotFoundException;
import com.academy.backend.review.domain.Review;
import com.academy.backend.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final EnrollmentRepository enrollmentRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Enrollment getEnrollmentByEnrollmentId(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException(enrollmentId));
    }

    @Transactional(readOnly = true)
    public Review getReviewByReviewId(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }
}
