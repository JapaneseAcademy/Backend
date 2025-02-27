package com.academy.backend.timeBlock.converter;

import com.academy.backend.exception.course.TimeTableMappingException;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.timeBlock.dto.response.TimeTableResponse;

public class TimeTableConverter {

    public static TimeTableResponse toTimeTableResponse(TimeBlock timeBlock) {
        try {
            return TimeTableResponse.builder()
                    .weekday(timeBlock.getWeekday())
                    .startTime(timeBlock.getStartTime())
                    .endTime(timeBlock.getEndTime())
                    .build();
        } catch (Exception e) {
            throw new TimeTableMappingException();
        }
    }
}
