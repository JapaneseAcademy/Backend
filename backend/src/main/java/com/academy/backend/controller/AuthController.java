package com.academy.backend.controller;

import com.academy.backend.dto.member.JoinRequest;
import com.academy.backend.dto.request.oauth.KakaoOAuthRequest;
import com.academy.backend.dto.request.oauth.RefreshTokenRequest;
import com.academy.backend.dto.response.oauth.LoginResponse;
import com.academy.backend.dto.response.oauth.RefreshTokenResponse;
import com.academy.backend.service.member.MemberService;
import com.academy.backend.service.oauth.AuthService;
import com.academy.backend.service.oauth.KakaoOAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final KakaoOAuthService kakaoOAuthService;
    private final AuthService authService;

    @PostMapping("/members")
    public ResponseEntity<LoginResponse> joinMember(@Valid @RequestBody JoinRequest request) {
        LoginResponse loginResponse = memberService.joinMember(request);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/kakao")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestBody KakaoOAuthRequest request) {
        LoginResponse loginResponse = kakaoOAuthService.kakaoLogin(request);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestHeader("Authorization") String authorizationHeader, @Valid @RequestBody RefreshTokenRequest request) {
        RefreshTokenResponse response = authService.refresh(authorizationHeader, request);

        return ResponseEntity.ok(response);
    }
}
