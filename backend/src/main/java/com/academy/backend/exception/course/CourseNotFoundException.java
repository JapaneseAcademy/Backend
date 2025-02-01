package com.academy.backend.exception.course;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends BaseException {
    public CourseNotFoundException(Long courseId) {
        super("Course not found with courseId: " + courseId, "COURSE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
