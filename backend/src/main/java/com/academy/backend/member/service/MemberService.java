package com.academy.backend.member.service;

import com.academy.backend.member.domain.Member;
import com.academy.backend.member.dto.request.JoinRequest;
import com.academy.backend.auth.dto.response.LoginResponse;
import com.academy.backend.member.dto.response.MemberResponse;

public interface MemberService {
    Member getMemberByLoginId(String loginId);
    LoginResponse joinMember(JoinRequest request);
    MemberResponse getProfile();
}
