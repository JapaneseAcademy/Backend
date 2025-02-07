package com.academy.backend.dto.response.course;

import com.academy.backend.domain.timetable.TimeTable;
import com.academy.backend.domain.timetable.Weekday;
import com.academy.backend.exception.course.TimeTableMappingException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class TimeTableResponse {

    private Long id;
    private Weekday weekday;
    private LocalTime startTime;
    private LocalTime endTime;

    public static TimeTableResponse of(TimeTable timeTable) {
        try {
            return TimeTableResponse.builder()
                    .id(timeTable.getId())
                    .weekday(timeTable.getWeekday())
                    .startTime(timeTable.getStartTime())
                    .endTime(timeTable.getEndTime())
                    .build();
        } catch (Exception e) {
            throw new TimeTableMappingException();
        }
    }
}
