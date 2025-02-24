package com.academy.backend.auth.controller;

import com.academy.backend.member.dto.request.JoinRequest;
import com.academy.backend.auth.dto.request.KakaoOAuthRequest;
import com.academy.backend.auth.dto.request.RefreshTokenRequest;
import com.academy.backend.auth.dto.response.LoginResponse;
import com.academy.backend.auth.dto.response.RefreshTokenResponse;
import com.academy.backend.member.service.MemberService;
import com.academy.backend.auth.service.AuthService;
import com.academy.backend.auth.service.KakaoOAuthService;
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
