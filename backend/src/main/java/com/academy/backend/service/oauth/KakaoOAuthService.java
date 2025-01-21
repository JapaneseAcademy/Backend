package com.academy.backend.service.oauth;

import com.academy.backend.dto.response.oauth.LoginResponse;

public interface KakaoOAuthService {
    public LoginResponse kakaoLogin(String authorizationCode);
    public String getAccessToken(String authorizationCode);
    public Long getKakaoId(String accessToken);

}
