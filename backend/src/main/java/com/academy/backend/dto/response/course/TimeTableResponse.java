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

    private Weekday weekday;
    private LocalTime startTime;
    private LocalTime endTime;
    private String classroom;

    public static TimeTableResponse of(TimeTable timeTable) {
        try {
            return TimeTableResponse.builder()
                    .weekday(timeTable.getWeekday())
                    .startTime(timeTable.getStartTime())
                    .endTime(timeTable.getEndTime())
                    .classroom(timeTable.getClassroom())
                    .build();
        } catch (Exception e) {
            throw new TimeTableMappingException();
        }
    }
}
