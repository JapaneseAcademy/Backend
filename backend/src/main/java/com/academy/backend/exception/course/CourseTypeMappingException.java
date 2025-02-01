package com.academy.backend.exception.course;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class CourseTypeMappingException extends BaseException {
    public CourseTypeMappingException() {
        super("CourseType dto mapping error", "COURSETYPE_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
