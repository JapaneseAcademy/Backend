package com.academy.backend.dto.request.enrollment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreateRequest {

    @NotNull(message = "리뷰 대상 강의는 필수 입력 사항입니다.")
    private Long enrollmentId;

    @NotBlank(message = "리뷰 내용은 필수 입력 사항입니다.")
    private String review;

    @NotNull(message = "익명 여부는 필수 입력 사항입니다.")
    private Boolean isAnonymous;
}
