package com.academy.backend.controller;

import com.academy.backend.dto.request.oauth.KakaoOAuthRequest;
import com.academy.backend.dto.response.oauth.LoginResponse;
import com.academy.backend.service.oauth.KakaoOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final KakaoOAuthService kakaoOAuthService;

    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody KakaoOAuthRequest requestDto) {
        String authorizationCode = requestDto.getAuthorizationCode();
        LoginResponse loginResponse = kakaoOAuthService.kakaoLogin(authorizationCode);

        return ResponseEntity.ok(loginResponse);
    }
}
