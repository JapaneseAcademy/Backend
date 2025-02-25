package com.academy.backend.enrollment.converter;

import com.academy.backend.enrollment.domain.Enrollment;
import com.academy.backend.enrollment.dto.response.EnrollmentDetailResponse;
import com.academy.backend.enrollment.dto.response.EnrollmentResponse;
import com.academy.backend.exception.enrollment.EnrollmentMappingException;
import com.academy.backend.review.converter.ReviewConverter;
import com.academy.backend.review.domain.Review;

import java.util.Optional;

public class EnrollmentConverter {

    public static EnrollmentResponse toEnrollmentResponse(Enrollment enrollment, Boolean reviewed) {
        try {
            return EnrollmentResponse.builder()
                    .enrollmentId(enrollment.getId())
                    .memberId(enrollment.getMember().getId())
                    .courseId(enrollment.getCourse().getId())
                    .courseTitle(enrollment.getCourse().getTitle())
                    .category(enrollment.getCategory())
                    .paymentAmount(enrollment.getPaymentAmount())
                    .paymentAt(enrollment.getPaymentAt())
                    .startDate(enrollment.getStartDate())
                    .endDate(enrollment.getEndDate())
                    .reviewed(reviewed)
                    .build();
        } catch (Exception e) {
            throw new EnrollmentMappingException();
        }
    }

    public static EnrollmentDetailResponse toEnrollmentDetailResponse(Enrollment enrollment, Review review) {

        try {
            return EnrollmentDetailResponse.builder()
                    .enrollmentId(enrollment.getId())
                    .courseId(enrollment.getCourse().getId())
                    .courseTitle(enrollment.getCourse().getTitle())
                    .category(enrollment.getCategory())
                    .paymentAmount(enrollment.getPaymentAmount())
                    .paymentAt(enrollment.getPaymentAt())
                    .startDate(enrollment.getStartDate())
                    .endDate(enrollment.getEndDate())
                    .reviewResponse(Optional.ofNullable(review)
                                        .map(ReviewConverter::toReviewResponse)
                                        .orElse(null))
                    .build();
        } catch (Exception e) {
            throw new EnrollmentMappingException();
        }
    }
}
