package com.academy.backend.controller;

import com.academy.backend.dto.request.JoinRequest;
import com.academy.backend.dto.response.JoinResponse;
import com.academy.backend.dto.response.oauth.LoginResponse;
import com.academy.backend.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {




}
