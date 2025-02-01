package com.academy.backend.dto.request;

import com.academy.backend.domain.course.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseTypeRequest {

    @NotNull(message = "수업 유형은 필수 입력 사항입니다.")
    private Category category;

    @NotNull(message = "수강료는 필수 입력 사항입니다.")
    private int cost;
}
