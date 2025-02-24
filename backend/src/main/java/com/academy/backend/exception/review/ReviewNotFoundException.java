package com.academy.backend.exception.review;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends BaseException {

    public ReviewNotFoundException(Long reviewId) {
        super("Review not found with id: " + reviewId, "REVIEW_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
