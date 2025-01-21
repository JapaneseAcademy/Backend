package com.academy.backend.exception.oauth;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class KakaoTokenResponseException extends BaseException {
    public KakaoTokenResponseException() {
        super("Kakao information with token failed", "KAKAO TOKEN ERROR", HttpStatus.BAD_REQUEST);
    }
}
