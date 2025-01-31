package com.academy.backend.exception.course;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class TagNotFoundException extends BaseException {
    public TagNotFoundException(String label) {
        super("Tag not found with label: " + label, "TAG_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
