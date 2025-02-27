package com.academy.backend.timeBlock.dto.response;

import com.academy.backend.timeBlock.domain.Weekday;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class TimeBlockResponse {

    private Weekday weekday;
    private LocalTime startTime;
    private LocalTime endTime;
}
