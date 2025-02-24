package com.academy.backend.config.auth;

import com.academy.backend.config.jwt.JwtProvider;
import com.academy.backend.member.domain.Role;
import com.academy.backend.auth.dto.jwt.AuthToken;
import com.academy.backend.auth.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthTokenGenerator {

    private static final String BEARER_TYPE = "Bearer";

    // access token: 1일, refresh token: 14일
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14;

    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public AuthToken generate(String loginId, Role role) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = jwtProvider.accessTokenGenerate(loginId, role, accessTokenExpiredAt);
        String refreshToken = jwtProvider.refreshTokenGenerate(refreshTokenExpiredAt);
        refreshTokenService.saveRefreshToken(loginId, role, refreshToken, REFRESH_TOKEN_EXPIRE_TIME);

        return AuthToken.of(BEARER_TYPE, accessToken, refreshToken);
    }
}
