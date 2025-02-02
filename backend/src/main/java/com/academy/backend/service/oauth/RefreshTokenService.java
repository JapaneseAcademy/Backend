package com.academy.backend.service.oauth;

import com.academy.backend.config.jwt.JwtProvider;
import com.academy.backend.domain.member.Role;
import com.academy.backend.dto.request.oauth.RefreshTokenRequest;
import com.academy.backend.dto.response.oauth.RefreshTokenResponse;
import com.academy.backend.exception.oauth.InvalidRefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final StringRedisTemplate redisTemplate;
    private final JwtProvider jwtProvider;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14;

    private String getKey(String id) {
        return "refreshToken:" + id;
    }

    // Refresh Token 저장
    public void saveRefreshToken(String id, Role role, String refreshToken, long duration) {
        redisTemplate.opsForValue().set(getKey(id), role + ":" + refreshToken, Duration.ofMillis(duration));
    }

    // Refresh Token 조회
    public String getRefreshToken(String id) {
        return redisTemplate.opsForValue().get(getKey(id));
    }

    // Refresh Token 삭제
    public void deleteRefreshToken(String id) {
        redisTemplate.delete(getKey(id));
    }

    public RefreshTokenResponse refresh(String header, RefreshTokenRequest request) {
        // refresh token 있는지 확인
        String inputToken = checkRefreshToken(header);
        String loginId = request.getLoginId();

        // refresh token 유효성 확인
        String role = validateRefreshToken(inputToken, loginId);

        // 새로운 access token, refresh token 발급
        return generateToken(loginId, role);
    }

    private String validateRefreshToken(String refreshToken, String loginId) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new InvalidRefreshTokenException("INVALID_REFRESH_TOKEN");
        }

        String storedToken = getRefreshToken(loginId);
        if (storedToken == null || !refreshToken.equals(storedToken.split(":")[1])) {
            throw new InvalidRefreshTokenException("INVALID_REFRESH_TOKEN");
        }

        return storedToken.split(":")[0];
    }

    private String checkRefreshToken(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new InvalidRefreshTokenException();
        }

        return header.substring(7);
    }

    private RefreshTokenResponse generateToken(String loginId, String role) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = jwtProvider.accessTokenGenerate(loginId, Role.valueOf(role), accessTokenExpiredAt);
        String refreshToken = jwtProvider.refreshTokenGenerate(refreshTokenExpiredAt);
        saveRefreshToken(loginId, Role.valueOf(role), refreshToken, REFRESH_TOKEN_EXPIRE_TIME);

        return RefreshTokenResponse.of(accessToken, refreshToken);
    }
}
