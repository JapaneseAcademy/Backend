package com.academy.backend.exception.oauth;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends BaseException {
    public InvalidRefreshTokenException() {
        super("Refresh token is required", "EMPTY_REFRESH_TOKEN", HttpStatus.UNAUTHORIZED);
    }

    public InvalidRefreshTokenException(String errorCode) {
        super("Refresh token expired or invalid", errorCode, HttpStatus.UNAUTHORIZED);
    }
}
