package com.academy.backend.exception.member;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(String loginId) {
        super("User already exists with login ID " + loginId, "USER_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
    }
}
