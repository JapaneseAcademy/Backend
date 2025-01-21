package com.academy.backend.service.member;

import com.academy.backend.domain.member.Member;
import com.academy.backend.dto.response.MemberResponse;
import com.academy.backend.exception.member.UserNotFoundException;
import com.academy.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberResponse getMemberByLoginId(String provider, Long userId) {

        String loginId = provider + userId;
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new UserNotFoundException(loginId)
        );

        return MemberResponse.of(member);
    }
}
