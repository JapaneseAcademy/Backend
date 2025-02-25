package com.academy.backend.exception.enrollment;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class EnrollmentMappingException extends BaseException {
    public EnrollmentMappingException() {
        super("Enrollment dto mapping error", "ENROLLMENT_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
