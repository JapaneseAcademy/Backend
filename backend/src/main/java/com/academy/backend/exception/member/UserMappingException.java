package com.academy.backend.exception.member;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class UserMappingException extends BaseException {

    public UserMappingException() {
        super("User dto mapping error", "USER_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
