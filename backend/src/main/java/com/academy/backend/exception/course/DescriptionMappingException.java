package com.academy.backend.exception.course;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class DescriptionMappingException extends BaseException {
    public DescriptionMappingException() {
        super("Description dto mapping error", "DESCRIPTION_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
