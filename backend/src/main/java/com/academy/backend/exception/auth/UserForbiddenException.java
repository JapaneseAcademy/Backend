package com.academy.backend.exception.auth;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class UserForbiddenException extends BaseException {

    public UserForbiddenException(Long memberId) {
        super("Unavailable with member id: " + memberId, "USER_FORBIDDEN_ERROR", HttpStatus.FORBIDDEN);
    }
}
