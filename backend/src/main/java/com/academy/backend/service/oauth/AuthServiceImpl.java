package com.academy.backend.service.oauth;

import com.academy.backend.config.auth.AuthTokenGenerator;
import com.academy.backend.config.jwt.JwtProvider;
import com.academy.backend.domain.member.Role;
import com.academy.backend.dto.jwt.AuthToken;
import com.academy.backend.dto.request.oauth.RefreshTokenRequest;
import com.academy.backend.dto.response.oauth.RefreshTokenResponse;
import com.academy.backend.exception.oauth.InvalidRefreshTokenException;
import com.academy.backend.exception.oauth.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final RefreshTokenService refreshTokenService;

    private final JwtProvider jwtProvider;
    private final AuthTokenGenerator authTokenGenerator;

    @Override
    @Transactional
    public RefreshTokenResponse refresh(String header, RefreshTokenRequest request) {
        // refresh token 있는지 확인
        String inputToken = extractToken(header);
        String loginId = request.getLoginId();

        // refresh token 유효성 확인
        String role = validateRefreshToken(inputToken, loginId);

        // 새로운 access token, refresh token 발급
        AuthToken token = authTokenGenerator.generate(loginId, Role.valueOf(role));

        return RefreshTokenResponse.of(token);
    }

    private String validateRefreshToken(String refreshToken, String loginId) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new InvalidRefreshTokenException();
        }

        String storedToken = refreshTokenService.getRefreshToken(loginId);
        if (storedToken == null || !refreshToken.equals(storedToken.split(":")[1])) {
            throw new InvalidRefreshTokenException();
        }

        return storedToken.split(":")[0];
    }

    private String extractToken(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new TokenNotFoundException();
        }

        return header.substring(7);
    }

    // TODO: 로그아웃 로직 구현
    @Override
    @Transactional
    public void logout(String header) {
        String token = extractToken(header);
        String loginId = jwtProvider.getLoginIdFromAccessToken(token);
        refreshTokenService.deleteRefreshToken(loginId);
    }
}
