package com.academy.backend.config.auth;

import com.academy.backend.config.jwt.JwtProvider;
import com.academy.backend.dto.jwt.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthTokenGenerator {

    private static final String BEARER_TYPE = "Bearer";

    // access token: 1일, refresh token: 14일
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14;

    private final JwtProvider jwtProvider;

    public AuthToken generate(String loginId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = jwtProvider.accessTokenGenerate(loginId, accessTokenExpiredAt);
        String refreshToken = jwtProvider.refreshTokenGenerate(refreshTokenExpiredAt);

        return AuthToken.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }
}
