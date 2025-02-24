package com.academy.backend.exception.review;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class ReviewMappingException extends BaseException {

    public ReviewMappingException() {
        super("Review dto mapping error", "REVIEW_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
