package com.academy.backend.exception.enrollment;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class UnavailableCategoryException extends BaseException {
    public UnavailableCategoryException(Long courseId) {
        super("Category unavailable with course id: " + courseId, "UNAVAILABLE_CATEGORY_ERROR", HttpStatus.BAD_REQUEST);
    }
}
