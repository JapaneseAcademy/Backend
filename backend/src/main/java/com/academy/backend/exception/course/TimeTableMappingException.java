package com.academy.backend.exception.course;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class TimeTableMappingException extends BaseException {
    public TimeTableMappingException() {
        super("TimeTable dto mapping error", "TIMETABLE_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
