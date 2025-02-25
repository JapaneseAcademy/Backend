package com.academy.backend.exception.auth;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends BaseException {
    public InvalidRefreshTokenException() {
        super("Refresh token expired or invalid", "INVALID_REFRESH_TOKEN", HttpStatus.UNAUTHORIZED);
    }
}
