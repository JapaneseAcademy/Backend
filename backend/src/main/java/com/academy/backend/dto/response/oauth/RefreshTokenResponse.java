package com.academy.backend.dto.response.oauth;

import com.academy.backend.dto.jwt.AuthToken;
import com.academy.backend.exception.oauth.TokenMappingException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenResponse {

    private String accessToken;
    private String refreshToken;

    public static RefreshTokenResponse of(AuthToken token) {
        try {
            return RefreshTokenResponse.builder()
                    .accessToken(token.getAccessToken())
                    .refreshToken(token.getRefreshToken())
                    .build();
        } catch (Exception e) {
            throw new TokenMappingException();
        }
    }
}
