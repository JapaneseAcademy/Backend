package com.academy.backend.exception.review;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateReviewException extends BaseException {
    public DuplicateReviewException(Long enrollmentId) {
        super("Review already exists with enrollment id: " + enrollmentId, "DUPLICATE_REVIEW_ERROR", HttpStatus.CONFLICT);
    }
}
