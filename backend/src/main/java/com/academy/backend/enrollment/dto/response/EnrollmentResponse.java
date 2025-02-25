package com.academy.backend.enrollment.dto.response;

import com.academy.backend.enrollment.domain.Category;
import com.academy.backend.enrollment.domain.Enrollment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class EnrollmentResponse {

    private Long id;
    private Long memberId;
    private Long courseId;
    private String courseTitle;
    private Category category;
    private Integer paymentAmount;
    private LocalDateTime paymentAt;
    private LocalDate startDate;
    private LocalDate endDate;
}
