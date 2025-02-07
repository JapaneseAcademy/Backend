package com.academy.backend.exception.oauth;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class TokenNotFoundException extends BaseException {
    public TokenNotFoundException() {
        super("Token is required", "EMPTY_TOKEN_ERROR", HttpStatus.UNAUTHORIZED);
    }
}
