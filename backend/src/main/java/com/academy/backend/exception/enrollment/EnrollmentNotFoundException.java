package com.academy.backend.exception.enrollment;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class EnrollmentNotFoundException extends BaseException {
    public EnrollmentNotFoundException(Long enrollmentId) {
        super("Enrollment not found with id: " + enrollmentId, "ENROLLMENT_NOT_FOUND_ERROR", HttpStatus.NOT_FOUND);
    }
}
