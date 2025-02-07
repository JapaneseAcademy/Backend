package com.academy.backend.service.oauth;

import com.academy.backend.dto.request.oauth.RefreshTokenRequest;
import com.academy.backend.dto.response.oauth.RefreshTokenResponse;

public interface AuthService {
    RefreshTokenResponse refresh(String header, RefreshTokenRequest request);

    String extractToken(String header);
    void logout(String header);
}
