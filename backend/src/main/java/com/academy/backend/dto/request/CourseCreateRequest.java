package com.academy.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreateRequest {

    @NotNull(message = "강의 담당 강사의 id는 필수 입력 정보입니다.")
    private Long memberId;

    @NotBlank(message = "강의 제목은 필수 입력 사항입니다.")
    @Size(max = 100, message = "제목은 100자 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "강의 설명은 필수 입력 사항입니다.")
    @Size(max = 500, message = "설명은 500자 이내로 입력해주세요.")
    private String description;
}
