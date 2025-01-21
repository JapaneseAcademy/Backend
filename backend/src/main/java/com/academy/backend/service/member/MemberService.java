package com.academy.backend.service.member;

import com.academy.backend.dto.response.MemberResponse;

public interface MemberService {
    public MemberResponse getMemberByLoginId(String provider, Long userId);
}
