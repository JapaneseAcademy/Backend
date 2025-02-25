package com.academy.backend.enrollment.converter;

import com.academy.backend.enrollment.domain.Enrollment;
import com.academy.backend.enrollment.dto.response.EnrollmentResponse;
import com.academy.backend.exception.enrollment.EnrollmentMappingException;

public class EnrollmentConverter {

    public static EnrollmentResponse toEnrollmentResponse(Enrollment enrollment) {
        try {
            return EnrollmentResponse.builder()
                    .id(enrollment.getId())
                    .memberId(enrollment.getMember().getId())
                    .courseId(enrollment.getCourse().getId())
                    .courseTitle(enrollment.getCourse().getTitle())
                    .category(enrollment.getCategory())
                    .paymentAmount(enrollment.getPaymentAmount())
                    .paymentAt(enrollment.getPaymentAt())
                    .startDate(enrollment.getStartDate())
                    .endDate(enrollment.getEndDate())
                    .build();
        } catch (Exception e) {
            throw new EnrollmentMappingException();
        }
    }
}
