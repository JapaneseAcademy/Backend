package com.academy.backend.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;

    public static AuthToken of(String accessToken, String refreshToken, String grantType, Long expiresIn) {
        return AuthToken.builder()
                .grantType(grantType)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(expiresIn)
                .build();
    }
}
