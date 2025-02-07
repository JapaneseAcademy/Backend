package com.academy.backend.service.member;

import com.academy.backend.config.auth.AuthTokenGenerator;
import com.academy.backend.domain.member.Member;
import com.academy.backend.domain.member.Role;
import com.academy.backend.dto.jwt.AuthToken;
import com.academy.backend.dto.request.member.JoinRequest;
import com.academy.backend.dto.response.member.MemberResponse;
import com.academy.backend.dto.response.oauth.LoginResponse;
import com.academy.backend.exception.member.UserAlreadyExistsException;
import com.academy.backend.exception.member.UserNotFoundException;
import com.academy.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final AuthTokenGenerator authTokenGenerator;

    @Override
    @Transactional
    public LoginResponse joinMember(JoinRequest request) {

        validateMemberDoesNotExist(request.getLoginId());

        Member member = Member.builder()
                .loginId(request.getLoginId())
                .name(request.getName())
                .phone(request.getPhone())
                .birth(request.getBirth())
                .role(Role.STUDENT)
                .build();

        memberRepository.save(member);
        AuthToken token = authTokenGenerator.generate(member.getLoginId(), member.getRole());

        return new LoginResponse(member, token, false);
    }

    @Transactional(readOnly = true)
    public void validateMemberDoesNotExist(String loginId) {
        if (memberRepository.findByLoginId(loginId).isPresent()) {
            throw new UserAlreadyExistsException(loginId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new UserNotFoundException(loginId)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }
}
