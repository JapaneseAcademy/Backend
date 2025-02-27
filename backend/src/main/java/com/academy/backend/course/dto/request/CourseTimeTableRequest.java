package com.academy.backend.course.dto.request;

import com.academy.backend.timeBlock.domain.Weekday;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class CourseTimeTableRequest {

    @NotNull(message = "요일은 필수 입력 사항입니다.")
    private Weekday weekday;

    @NotNull(message = "시작 시간은 필수 입력 사항입니다.")
    private LocalTime startTime;

    @NotNull(message = "종료 시간은 필수 입력 사항입니다.")
    private LocalTime endTime;
}
