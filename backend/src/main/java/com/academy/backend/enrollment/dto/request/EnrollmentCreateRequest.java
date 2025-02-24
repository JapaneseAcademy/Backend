package com.academy.backend.enrollment.dto.request;

import com.academy.backend.enrollment.domain.Category;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EnrollmentCreateRequest {

    /***********************************
    * TODO: 결제 관련 필요한 필드 추가 필요함
    ************************************/

    @NotEmpty(message = "강의 id는 필수 입력 사항입니다.")
    private Long courseId;

    @NotBlank(message = "강의 수강 유형은 필수 입력 사항입니다.")
    private Category category;

    @NotBlank(message = "강의 수강 시작일은 필수 입력 사항입니다.")
    @FutureOrPresent(message = "강의 수강 시작일은 오늘 또는 미래 날짜여야 합니다.")
    private LocalDate startDate;

    @NotBlank(message = "강의 수강 종료일은 필수 입력 사항입니다.")
    @Future(message = "강의 수강 종료일은 미래 날짜여야 합니다.")
    private LocalDate endDate;
}
