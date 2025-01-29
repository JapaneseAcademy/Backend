package com.academy.backend.exception.course;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class CourseMappingException extends BaseException {
    public CourseMappingException() {
        super("Course dto mapping error", "COURSE_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
