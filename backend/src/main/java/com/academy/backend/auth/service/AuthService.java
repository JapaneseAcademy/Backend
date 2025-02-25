package com.academy.backend.auth.service;

import com.academy.backend.auth.dto.request.RefreshTokenRequest;
import com.academy.backend.auth.dto.response.RefreshTokenResponse;

public interface AuthService {
    RefreshTokenResponse refresh(String header, RefreshTokenRequest request);

    String extractToken(String header);
    void logout();
}
