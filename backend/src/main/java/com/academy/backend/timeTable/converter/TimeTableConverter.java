package com.academy.backend.timeTable.converter;

import com.academy.backend.exception.course.TimeTableMappingException;
import com.academy.backend.timeTable.domain.TimeTable;
import com.academy.backend.timeTable.dto.response.TimeTableResponse;

public class TimeTableConverter {

    public static TimeTableResponse toTimeTableResponse(TimeTable timeTable) {
        try {
            return TimeTableResponse.builder()
                    .weekday(timeTable.getWeekday())
                    .startTime(timeTable.getStartTime())
                    .endTime(timeTable.getEndTime())
                    .build();
        } catch (Exception e) {
            throw new TimeTableMappingException();
        }
    }
}
