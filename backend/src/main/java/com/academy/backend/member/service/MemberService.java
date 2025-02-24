package com.academy.backend.member.service;

import com.academy.backend.member.domain.Member;
import com.academy.backend.member.dto.request.JoinRequest;
import com.academy.backend.auth.dto.response.LoginResponse;

public interface MemberService {
    Member getMemberByLoginId(String loginId);
    Member getMemberById(Long id);
    LoginResponse joinMember(JoinRequest request);
}
