package com.academy.backend.auth.service;

import com.academy.backend.auth.dto.request.KakaoOAuthRequest;
import com.academy.backend.auth.dto.response.LoginResponse;

public interface KakaoOAuthService {
    public LoginResponse kakaoLogin(KakaoOAuthRequest request);
    public String getAccessToken(String authorizationCode);
    public Long getKakaoId(String accessToken);

}
