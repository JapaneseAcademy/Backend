package com.academy.backend.service.member;

import com.academy.backend.domain.member.Member;
import com.academy.backend.dto.request.JoinRequest;
import com.academy.backend.dto.response.MemberResponse;
import com.academy.backend.dto.response.oauth.LoginResponse;

public interface MemberService {
    public MemberResponse getMemberByLoginId(String provider, Long userId);
    public Member getMemberById(Long id);
    public LoginResponse joinMember(JoinRequest request);
}
