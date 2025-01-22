package com.academy.backend.controller;

import com.academy.backend.dto.request.JoinRequest;
import com.academy.backend.dto.request.oauth.KakaoOAuthRequest;
import com.academy.backend.dto.response.oauth.LoginResponse;
import com.academy.backend.service.member.MemberService;
import com.academy.backend.service.oauth.KakaoOAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final KakaoOAuthService kakaoOAuthService;

    @PostMapping("/members")
    public ResponseEntity<LoginResponse> joinMember(@Valid @RequestBody JoinRequest request) {
        LoginResponse loginResponse = memberService.joinMember(request);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody KakaoOAuthRequest request) {
        LoginResponse loginResponse = kakaoOAuthService.kakaoLogin(request);

        return ResponseEntity.ok(loginResponse);
    }
}
