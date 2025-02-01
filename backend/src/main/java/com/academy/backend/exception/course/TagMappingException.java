package com.academy.backend.exception.course;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class TagMappingException extends BaseException {
    public TagMappingException() {
        super("Tag dto mapping error", "TAG_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
