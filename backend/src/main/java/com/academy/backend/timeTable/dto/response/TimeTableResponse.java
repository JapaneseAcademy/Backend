package com.academy.backend.timeTable.dto.response;

import com.academy.backend.timeTable.domain.Weekday;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class TimeTableResponse {

    private Weekday weekday;
    private LocalTime startTime;
    private LocalTime endTime;
}
