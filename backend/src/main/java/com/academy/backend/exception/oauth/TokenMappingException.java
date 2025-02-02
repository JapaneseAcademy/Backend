package com.academy.backend.exception.oauth;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class TokenMappingException extends BaseException {
    public TokenMappingException() {
        super("Token dto mapping error", "TOKEN_MAPPING_ERROR", HttpStatus.BAD_REQUEST);
    }
}
