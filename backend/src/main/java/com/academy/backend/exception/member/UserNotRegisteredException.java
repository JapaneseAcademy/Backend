package com.academy.backend.exception.member;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotRegisteredException extends BaseException {
    public UserNotRegisteredException() {
        super("User not registered yet", "USER_NOT_REGISTERED", HttpStatus.NOT_FOUND);
    }
}
