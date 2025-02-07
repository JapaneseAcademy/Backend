package com.academy.backend.service.member;

import com.academy.backend.domain.member.Member;
import com.academy.backend.dto.request.JoinRequest;
import com.academy.backend.dto.response.MemberResponse;
import com.academy.backend.dto.response.oauth.LoginResponse;

public interface MemberService {
    Member getMemberByLoginId(String loginId);
    Member getMemberById(Long id);
    LoginResponse joinMember(JoinRequest request);
}
