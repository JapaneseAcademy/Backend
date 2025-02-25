package com.academy.backend.member.controller;

import com.academy.backend.auth.service.AuthService;
import com.academy.backend.member.dto.response.MemberResponse;
import com.academy.backend.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();

        return ResponseEntity.ok().body("Logout success");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        MemberResponse response = memberService.getProfile();

        return ResponseEntity.ok(response);
    }
}
